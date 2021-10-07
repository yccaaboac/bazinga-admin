/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package clay.yccaaboac.modules.monitor.service.impl;

import clay.yccaaboac.modules.monitor.service.dto.OnlineUserDto;
import clay.yccaaboac.modules.security.config.bean.SecurityProperties;
import clay.yccaaboac.modules.security.service.dto.JwtUserDto;
import clay.yccaaboac.utils.EncryptUtils;
import clay.yccaaboac.utils.PageUtil;
import clay.yccaaboac.utils.RedisUtils;
import clay.yccaaboac.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author Zheng Jie
 */
@Service
@Slf4j
public class OnlineUserServiceImpl {

    private final SecurityProperties properties;
    private final RedisUtils redisUtils;

    public OnlineUserServiceImpl(SecurityProperties properties, RedisUtils redisUtils) {
        this.properties = properties;
        this.redisUtils = redisUtils;
    }

    /**
     * 保存在线用户信息
     *
     * @param jwtUserDto /
     * @param token      /
     * @param request    /
     */
    public void save(JwtUserDto jwtUserDto, String token, HttpServletRequest request) {
        String ip = StringUtils.getIp(request);
        String browser = StringUtils.getBrowser(request);
        String address = StringUtils.getCityInfo(ip);
        OnlineUserDto onlineUserDto = null;
        try {
            onlineUserDto = new OnlineUserDto(jwtUserDto.getUsername(), jwtUserDto.getUser().getNickName(), browser, ip, address, EncryptUtils.desEncrypt(token), new Date());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        redisUtils.set(properties.getOnlineKey() + token, onlineUserDto, properties.getTokenValidityInSeconds() / 1000);
    }

    /**
     * 查询全部数据
     *
     * @param filter   /
     * @param pageable /
     * @return /
     */
    public Map<String, Object> getAll(String filter, Pageable pageable) {
        List<OnlineUserDto> onlineUserDtos = getAll(filter);
        return PageUtil.toPage(
                PageUtil.toPage(pageable.getPageNumber(), pageable.getPageSize(), onlineUserDtos),
                onlineUserDtos.size()
        );
    }

    /**
     * 查询全部数据，不分页
     *
     * @param filter /
     * @return /
     */
    public List<OnlineUserDto> getAll(String filter) {
        List<String> keys = redisUtils.scan(properties.getOnlineKey() + "*");
        Collections.reverse(keys);
        List<OnlineUserDto> onlineUserDtos = new ArrayList<>();
        Field[] declaredFields = OnlineUserDto.class.getDeclaredFields();
        for (String key : keys) {
            OnlineUserDto onlineUserDto = (OnlineUserDto) redisUtils.get(key);
            if (StringUtils.isNotBlank(filter)) {
                for (int i = 0; i < declaredFields.length; i++) {
                    boolean b = declaredFields[i].isAccessible();
                    declaredFields[i].setAccessible(true);
                    try {
                        Object o = declaredFields[i].get(onlineUserDto);
                        if (o.toString().contains(filter)) {
                            onlineUserDtos.add(onlineUserDto);
                            break;
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } finally {
                        declaredFields[i].setAccessible(b);
                    }
                }
            } else {
                onlineUserDtos.add(onlineUserDto);
            }
        }
        onlineUserDtos.sort((o1, o2) -> o2.getLoginTime().compareTo(o1.getLoginTime()));
        return onlineUserDtos;
    }

//    /**
//     * 查询全部数据，不分页
//     * @param filter /
//     * @return /
//     */
//    public List<OnlineUserDto> getAll(String filter){
//        List<String> keys = redisUtils.scan(properties.getOnlineKey() + "*");
//        Collections.reverse(keys);
//        List<OnlineUserDto> onlineUserDtos = new ArrayList<>();
//        for (String key : keys) {
//            OnlineUserDto onlineUserDto = (OnlineUserDto) redisUtils.get(key);
//            if(StringUtils.isNotBlank(filter)){
//                if(onlineUserDto.toString().contains(filter)){
//                    onlineUserDtos.add(onlineUserDto);
//                }
//            } else {
//                onlineUserDtos.add(onlineUserDto);
//            }
//        }
//        onlineUserDtos.sort((o1, o2) -> o2.getLoginTime().compareTo(o1.getLoginTime()));
//        return onlineUserDtos;
//    }

    /**
     * 踢出用户
     *
     * @param key /
     */
    public void kickOut(String key) {
        key = properties.getOnlineKey() + key;
        redisUtils.del(key);
    }

    /**
     * 退出登录
     *
     * @param token /
     */
    public void logout(String token) {
        String key = properties.getOnlineKey() + token;
        redisUtils.del(key);
    }

    /**
     * 查询用户
     *
     * @param key /
     * @return /
     */
    public OnlineUserDto getOne(String key) {
        return (OnlineUserDto) redisUtils.get(key);
    }

    /**
     * 检测用户是否在之前已经登录，已经登录踢下线
     *
     * @param userName 用户名
     */
    public void checkLoginOnUser(String userName, String igoreToken) {
        List<OnlineUserDto> onlineUserDtos = getAll(userName);
        if (onlineUserDtos == null || onlineUserDtos.isEmpty()) {
            return;
        }
        for (OnlineUserDto onlineUserDto : onlineUserDtos) {
            if (onlineUserDto.getUserName().equals(userName)) {
                try {
                    String token = EncryptUtils.desDecrypt(onlineUserDto.getKey());
                    if (StringUtils.isNotBlank(igoreToken) && !igoreToken.equals(token)) {
                        this.kickOut(token);
                    } else if (StringUtils.isBlank(igoreToken)) {
                        this.kickOut(token);
                    }
                } catch (Exception e) {
                    log.error("checkUser is error", e);
                }
            }
        }
    }

    /**
     * 根据用户名强退用户
     *
     * @param username /
     */
    @Async
    public void kickOutForUsername(String username) throws Exception {
        List<OnlineUserDto> onlineUsers = getAll(username);
        for (OnlineUserDto onlineUser : onlineUsers) {
            if (onlineUser.getUserName().equals(username)) {
                String token = EncryptUtils.desDecrypt(onlineUser.getKey());
                kickOut(token);
            }
        }
    }
}
