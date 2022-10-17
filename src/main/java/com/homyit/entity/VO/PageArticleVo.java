package com.homyit.entity.VO;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class PageArticleVo {
    /**
     * 文章id
     * 规定序列化规则为Long to String
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章概要
     */
    private String summary;

    /**
     * 浏览量
     */
    private Long views;

    /**
     * 发布人
     */
    private String username;
}
