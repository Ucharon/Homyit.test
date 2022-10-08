package com.homyit.handler;


import com.homyit.domain.VO.ResultVO;
import com.homyit.exception.BizException;
import com.homyit.enums.ExceptionCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 字段校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        //从异常对象中拿到ObjectError对象
        String defaultMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return ResultVO.error(ExceptionCodeEnum.LOGIN_INPUT_ERROR, defaultMessage);
    }

    @ExceptionHandler(BizException.class)
    public ResultVO handleBizException(BizException e) {
        return ResultVO.error(e.getError());
    }

    @ExceptionHandler(Exception.class)
    public ResultVO<ExceptionCodeEnum> handleConstraintViolationException(Exception e) {
//        log.warn("错误: {}", e.getMessage(), e);
        // 一般只需返回泛化的错误信息，比如“参数错误”
        return ResultVO.error(ExceptionCodeEnum.TOKEN_ILLEGAL, e.getMessage());
    }

}
