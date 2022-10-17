package com.homyit.entity.DO.excelDo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;


@Data
public class UserExcelDo {

    /**
     * 用户名
     */
    @ExcelProperty(index = 0)
    private String userName;

    /**
     * 昵称
     */
    @ExcelProperty(index = 1)
    private String nickName;

    /**
     * 密码
     */
    @ExcelProperty(index = 2)
    private String password;

    /**
     * 邮箱
     */
    @ExcelProperty(index = 3)
    private String email;

    /**
     * 手机号
     */
    @ExcelProperty(index = 4)
    private String phonenumber;

    /**
     * 用户性别(0男，1女)
     */
    @ExcelProperty(index = 5)
    private String sex;
}
