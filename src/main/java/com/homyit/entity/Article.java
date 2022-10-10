package com.homyit.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
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
     */
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
     * 文章简介
     */
    private String summary;

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
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 点赞数
     */
    private Long likeCount;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}