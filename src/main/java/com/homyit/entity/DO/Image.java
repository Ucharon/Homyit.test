package com.homyit.entity.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName image
 */
@TableName(value ="image")
@Data
public class Image implements Serializable {
    /**
     * 图片id
     */
    @TableId
    private Long id;

    /**
     * 对应的文章id
     */
    private Long articleId;

    /**
     * 图片url
     */
    private String url;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}