package clay.yccaaboac.modules.security.rest;


import clay.yccaaboac.annotation.rest.AnonymousDeleteMapping;
import clay.yccaaboac.annotation.rest.AnonymousGetMapping;
import clay.yccaaboac.annotation.rest.AnonymousPostMapping;
import clay.yccaaboac.config.RsaProperties;
import clay.yccaaboac.exception.BadRequestException;
import clay.yccaaboac.modules.security.config.bean.LoginCodeEnum;
import clay.yccaaboac.modules.security.config.bean.LoginProperties;
import clay.yccaaboac.modules.security.config.bean.SecurityProperties;
import clay.yccaaboac.modules.security.security.TokenProvider;
import clay.yccaaboac.modules.monitor.service.impl.OnlineUserServiceImpl;
import clay.yccaaboac.modules.security.service.dto.AuthUserDto;
import clay.yccaaboac.modules.security.service.dto.JwtUserDto;
import clay.yccaaboac.utils.RedisUtils;
import clay.yccaaboac.utils.RsaUtils;
import clay.yccaaboac.utils.SecurityUtils;
import clay.yccaaboac.utils.StringUtils;
import cn.hutool.core.util.IdUtil;
import com.wf.captcha.base.Captcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * ???????????????token????????????????????????
 */
@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Api(tags = "???????????????????????????")
public class AuthorizationController {
    private final SecurityProperties properties;
    private final RedisUtils redisUtils;
    private final OnlineUserServiceImpl onlineUserServiceImpl;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    @Resource
    private LoginProperties loginProperties;

    @ApiOperation("????????????")
    @AnonymousPostMapping(value = "/login")
    public ResponseEntity<Object> login(@Validated @RequestBody AuthUserDto authUser, HttpServletRequest request) throws Exception {
        // ????????????
        String password = RsaUtils.decryptByPrivateKey(RsaProperties.privateKey, authUser.getPassword());
        // ???????????????
        String code = (String) redisUtils.get(authUser.getUuid());
        // ???????????????
        redisUtils.del(authUser.getUuid());
        if (StringUtils.isBlank(code)) {
            throw new BadRequestException("??????????????????????????????");
        }
        if (StringUtils.isBlank(authUser.getCode()) || !authUser.getCode().equalsIgnoreCase(code)) {
            throw new BadRequestException("???????????????");
        }
        //????????????authenticate()?????????????????????UserDetailsService.loadUserByUsername()??????????????????????????????????????????????????????????????????UsernamePasswordAuthenticationToken????????????SecurityContext.
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authUser.getUsername(), password);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // ????????????
        String token = tokenProvider.createToken(authentication);
        final JwtUserDto jwtUserDto = (JwtUserDto) authentication.getPrincipal();
        // ??????????????????
        onlineUserServiceImpl.save(jwtUserDto, token, request);
        // ?????? token ??? ????????????
        Map<String, Object> authInfo = new HashMap<String, Object>(2) {{
            put("token", properties.getTokenStartWith() + token);
            put("user", jwtUserDto);
        }};
        if (loginProperties.isSingleLogin()) {
            //???????????????????????????token
            onlineUserServiceImpl.checkLoginOnUser(authUser.getUsername(), token);
        }
        return ResponseEntity.ok(authInfo);
    }

    @ApiOperation("??????????????????")
    @GetMapping(value = "/info")
    public ResponseEntity<Object> getUserInfo() {
        return ResponseEntity.ok(SecurityUtils.getCurrentUser());
    }

    @ApiOperation("???????????????")
    @AnonymousGetMapping(value = "/code")
    public ResponseEntity<Object> getCode() {
        // ?????????????????????
        Captcha captcha = loginProperties.getCaptcha();
        String uuid = properties.getCodeKey() + IdUtil.simpleUUID();
        //????????????????????? arithmetic???????????? >= 2 ??????captcha.text()??????????????????????????????
        String captchaValue = captcha.text();
        if (captcha.getCharType() - 1 == LoginCodeEnum.arithmetic.ordinal() && captchaValue.contains(".")) {
            captchaValue = captchaValue.split("\\.")[0];
        }
        // ??????
        redisUtils.set(uuid, captchaValue, loginProperties.getLoginCode().getExpiration(), TimeUnit.MINUTES);
        // ???????????????
        Map<String, Object> imgResult = new HashMap<String, Object>(2) {{
            put("img", captcha.toBase64());
            put("uuid", uuid);
        }};
        return ResponseEntity.ok(imgResult);
    }

    @ApiOperation("????????????")
    @AnonymousDeleteMapping(value = "/logout")
    public ResponseEntity<Object> logout(HttpServletRequest request) {
        onlineUserServiceImpl.logout(tokenProvider.getToken(request));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
