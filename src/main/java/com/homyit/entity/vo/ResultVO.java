package com.homyit.entity.vo;

import com.homyit.enums.ExceptionCodeEnum;
import com.homyit.enums.ResultCodeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResultVO<T> {

    private Integer code;
    private String message;
    private T data;

    public ResultVO(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResultVO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 带数据成功返回
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultVO<T> success(T data) {
        return new ResultVO<>(ResultCodeEnum.SUCCESS.getCode(),
                ResultCodeEnum.SUCCESS.getMessage(), data);
    }

    /**
     * 未携带数据成功返回
     *
     * @param <T>
     * @return
     */
    public static <T> ResultVO<T> success() {
        return new ResultVO<>(ResultCodeEnum.SUCCESS.getCode(),
                ResultCodeEnum.SUCCESS.getMessage());
    }

    /**
     * 通用错误返回
     *
     * @param exceptionCodeEnum
     * @return
     */
    public static <T> ResultVO<T> error(ExceptionCodeEnum exceptionCodeEnum) {
        return new ResultVO<>(exceptionCodeEnum.getCode(),
                exceptionCodeEnum.getDesc());
    }

    /**
     * 通用错误返回
     *
     * @param exceptionCodeEnum
     * @param msg
     * @return
     */
    public static <T> ResultVO<T> error(ExceptionCodeEnum exceptionCodeEnum, String msg) {
        return new ResultVO<>(exceptionCodeEnum.getCode(), msg);
    }

    /**
     * 通用错误返回
     *
     * @param exceptionCodeEnum
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultVO<T> error(ExceptionCodeEnum exceptionCodeEnum, T data) {
        return new ResultVO<>(exceptionCodeEnum.getCode(),
                exceptionCodeEnum.getDesc(), data);
    }
}
