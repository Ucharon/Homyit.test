package com.homyit.enums;


import lombok.Getter;

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
    TOKEN_ILLEGAL(603, "token非法")
    ;

    private final Integer code;
    private final String desc;

    ExceptionCodeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }


}
