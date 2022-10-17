package com.homyit.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.homyit.entity.DO.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.homyit.entity.DTO.ArticlePageDto;
import com.homyit.entity.VO.ArticleVo;
import com.homyit.entity.VO.PageArticleVo;

/**
* @author charon
* @description 针对表【article】的数据库操作Service
* @createDate 2022-10-08 21:06:08
*/
public interface ArticleService extends IService<Article> {

    void insertArticle(Article article);

    Page<PageArticleVo> pageList(ArticlePageDto articlePageDto);

    ArticleVo getByIdWithUsername(Long id);
}
