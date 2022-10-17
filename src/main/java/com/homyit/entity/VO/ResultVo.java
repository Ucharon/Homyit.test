package com.homyit.entity.VO;

import com.homyit.enums.ExceptionCodeEnum;
import com.homyit.enums.ResultCodeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResultVo<T> {

    private Integer code;
    private String message;
    private T data;

    public ResultVo(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResultVo(Integer code, String message) {
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
    public static <T> ResultVo<T> success(T data) {
        return new ResultVo<>(ResultCodeEnum.SUCCESS.getCode(),
                ResultCodeEnum.SUCCESS.getMessage(), data);
    }

    /**
     * 未携带数据成功返回
     *
     * @param <T>
     * @return
     */
    public static <T> ResultVo<T> success() {
        return new ResultVo(ResultCodeEnum.SUCCESS.getCode(),
                ResultCodeEnum.SUCCESS.getMessage());
    }

    /**
     * 通用错误返回
     *
     * @param exceptionCodeEnum
     * @return
     */
    public static <T> ResultVo<T> error(ExceptionCodeEnum exceptionCodeEnum) {
        return new ResultVo(exceptionCodeEnum.getCode(),
                exceptionCodeEnum.getDesc());
    }

    /**
     * 通用错误返回
     *
     * @param exceptionCodeEnum
     * @param msg
     * @return
     */
    public static <T> ResultVo<T> error(ExceptionCodeEnum exceptionCodeEnum, String msg) {
        return new ResultVo(exceptionCodeEnum.getCode(), msg);
    }

    /**
     * 通用错误返回
     *
     * @param exceptionCodeEnum
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultVo<T> error(ExceptionCodeEnum exceptionCodeEnum, T data) {
        return new ResultVo<>(exceptionCodeEnum.getCode(),
                exceptionCodeEnum.getDesc(), data);
    }
}
