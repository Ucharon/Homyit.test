package com.homyit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.homyit.entity.LoginUser;
import com.homyit.entity.User;
import com.homyit.service.UserService;
import com.homyit.mapper.UserMapper;
import com.homyit.utils.JwtUtil;
import com.homyit.utils.RedisCache;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author charon
 * @description 针对表【user(用户表
 * <p>
 * )】的数据库操作Service实现
 * @createDate 2022-10-05 16:06:30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private RedisCache redisCache;

    /**
     * 登录
     * @param user
     * @return
     */
    @Override
    public Map<String, String> login(User user) {
        //AuthenticationManager authenticate进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        //认证通过，使用userId生成页jwt jwt存入ResponseResult返回
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);

        //把完到用户信息存入redis userId作为key
        redisCache.setCacheObject("login:" + userId, loginUser);

        return map;
    }

    /**
     * 退出登录
     */
    @Override
    public void logout() {
        //根据authentication获取到LoginUser对象
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long id = loginUser.getUser().getId();
        redisCache.deleteObject("login:" + id);
    }
}




