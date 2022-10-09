package com.homyit.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * 
 * @TableName article
 */
@TableName(value ="article")
@Data
public class Article implements Serializable {
    /**
     * 文章id
     * 规定序列化规则为Long to String
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId
    private Long id;

    /**
     * 发表用户id
     */
    private Long userId;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 浏览量
     */
    private Long views;

    /**
     * 评论总数
     */
    private Long commentCount;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 点赞数
     */
    private Long likeCount;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}