package com.homyit.handler;

import com.alibaba.fastjson.JSON;
import com.homyit.entity.VO.ResultVo;
import com.homyit.exception.BizException;
import com.homyit.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class FilterChainExceptionHandler extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        ResultVo<Object> result = null;
        try {
            filterChain.doFilter(request, response);
        } catch (BizException e) {
            log.error(e.getError().getDesc());
            result = ResultVo.error(e.getError());
            String jsonString = JSON.toJSONString(result);
            WebUtils.renderString(response, jsonString);
        }
    }
}
