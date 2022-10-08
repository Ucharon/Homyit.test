package com.homyit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.homyit.domain.LoginUser;
import com.homyit.domain.User;
import com.homyit.exception.BizException;
import com.homyit.enums.ExceptionCodeEnum;
import com.homyit.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, username);
        User user = userMapper.selectOne(queryWrapper);

        //如果没有查询到用户
        if (Objects.isNull(user)) {
            throw new BizException(ExceptionCodeEnum.LOGIN_ERROR);
        }

        //TODO 查询对应的权限信息


        //把数据封装成UserDetails返回
        return new LoginUser(user);
    }
}
