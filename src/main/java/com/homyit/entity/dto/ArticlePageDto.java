package com.homyit.entity.dto;

import lombok.Data;

@Data
public class ArticlePageDto extends PageDto{

    //根据文章名称查询
    private String title;

    //根据发布人信息查询
    private String username;
}
