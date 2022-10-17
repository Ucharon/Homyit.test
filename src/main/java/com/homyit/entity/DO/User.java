package com.homyit.entity.DO;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 用户表


 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 用户名
     */
    @NotNull(message = "用户账号不能为空")
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 密码
     */
    @NotNull(message = "用户密码不能为空")
    private String password;

    /**
     * 账号状态(1正常，0关闭)
     */
    private String status;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phonenumber;

    /**
     * 用户性别(0男，1女)
     */
    private String sex;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 用户类型(0管理员，1普通用户)
     */
    private String userType;

    /**
     * 创建人的用户id
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 删除标志(0代表未删除，1已删除)
     */
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}