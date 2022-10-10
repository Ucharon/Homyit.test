package com.homyit.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.homyit.entity.Article;
import com.homyit.entity.dto.ArticlePageDto;
import com.homyit.entity.vo.PageArticleVo;
import com.homyit.entity.vo.ResultVo;
import com.homyit.service.ArticleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 文章相关的接口
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    ArticleService articleService;

    /**
     * 添加文章
     * 权限：publish_article
     * @return
     */
    @PreAuthorize("hasAuthority('publish_article')")
    @PostMapping
    public ResultVo insertArticle(@Validated @RequestBody Article article) {
        articleService.insertArticle(article);

        return ResultVo.success();
    }

    /**
     * 分页获取文章
     * 权限：无
     */
    @PostMapping("/page")
    public ResultVo<Page<PageArticleVo>> page(@RequestBody ArticlePageDto articlePageDto) {
        Page<PageArticleVo> page = articleService.pageList(articlePageDto);

        return ResultVo.success(page);
    }



}
