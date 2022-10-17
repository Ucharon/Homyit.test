package com.homyit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.homyit.entity.DO.LoginUser;
import com.homyit.entity.DO.User;
import com.homyit.exception.BizException;
import com.homyit.enums.ExceptionCodeEnum;
import com.homyit.mapper.MenuMapper;
import com.homyit.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private MenuMapper menuMapper;

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

        //查询对应的权限信息
        List<String> list = menuMapper.selectPermsByUserId(user.getId());
//        log.info(list.toString());

        //把数据封装成UserDetails返回
        return new LoginUser(user, list);
    }
}
