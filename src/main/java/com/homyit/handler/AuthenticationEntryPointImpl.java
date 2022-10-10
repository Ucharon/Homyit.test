package com.homyit.handler;

import com.alibaba.fastjson.JSON;
import com.homyit.entity.vo.ResultVo;
import com.homyit.enums.ExceptionCodeEnum;
import com.homyit.utils.WebUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) {
        ResultVo<Object> result = null;

        if (BadCredentialsException.class == authException.getClass()) {
            //为用户名密码错误
            result = ResultVo.error(ExceptionCodeEnum.LOGIN_ERROR);
        } else if (InsufficientAuthenticationException.class == authException.getClass()) {
            result = ResultVo.error(ExceptionCodeEnum.LOGIN_INFORMATION_ILLEGAL);
        }

        String jsonString = JSON.toJSONString(result);
        WebUtils.renderString(response, jsonString);
    }
}