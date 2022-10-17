package com.homyit.enums;


import lombok.Getter;
import lombok.ToString;

/**
 * 通用错误枚举
 */

@Getter
public enum ExceptionCodeEnum {

    /**
     * 用户登录
     */
    NEED_LOGIN(600, "用户未登录"),
    LOGIN_ERROR(601, "用户名或密码错误"),
    LOGIN_INPUT_ERROR(602, "输入格式不正确"),
    LOGIN_INFORMATION_ILLEGAL(603, "登录信息失效"),

    /**
     * 权限
     */
    FORBIDDEN(700, "权限不足"),


    /**
     * 文件和图片
     */
    UPLOAD_FAIL(800, "文件或图片上传失败"),


    /**
     * 参数
     */
    ERROR_PARAM(900, "参数错误"),

    /**
     * Excel操作
     */
    EXCEL_ERROR(1000, "EXCEL格式错误")
    ;

    private final Integer code;
    private final String desc;

    ExceptionCodeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
