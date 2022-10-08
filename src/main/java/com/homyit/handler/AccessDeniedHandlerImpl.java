package com.homyit.handler;

import com.alibaba.fastjson.JSON;
import com.homyit.entity.vo.ResultVO;
import com.homyit.enums.ExceptionCodeEnum;
import com.homyit.util.WebUtils;
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
        ResultVO resultVO = ResultVO.error(ExceptionCodeEnum.FORBIDDEN);
        String jsonString = JSON.toJSONString(resultVO);
        WebUtils.renderString(response, jsonString);
    }
}
