package com.homyit.handler;

import com.alibaba.fastjson.JSON;
import com.homyit.entity.vo.ResultVo;
import com.homyit.enums.ExceptionCodeEnum;
import com.homyit.utils.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResultVo resultVO = ResultVo.error(ExceptionCodeEnum.FORBIDDEN);
        String jsonString = JSON.toJSONString(resultVO);
        WebUtils.renderString(response, jsonString);
    }
}
