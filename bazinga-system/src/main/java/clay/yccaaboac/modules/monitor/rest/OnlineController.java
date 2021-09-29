package clay.yccaaboac.modules.monitor.rest;

import clay.yccaaboac.modules.monitor.service.impl.OnlineUserServiceImpl;
import clay.yccaaboac.utils.EncryptUtils;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/online")
public class OnlineController {
    private final OnlineUserServiceImpl onlineUserService;

    @ApiOperation("查询在线用户")
    @GetMapping
    public ResponseEntity<Object> query(String filter, Pageable pageable){
        return new ResponseEntity<>(onlineUserService.getAll(filter,pageable), HttpStatus.OK);
    }

    @ApiOperation("踢出用户")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Set<String> keys) throws Exception {
        for (String key : keys) {
            // 解密Key
            key = EncryptUtils.desDecrypt(key);
            onlineUserService.kickOut(key);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
