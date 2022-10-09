package com.homyit.controller;

import com.homyit.entity.Article;
import com.homyit.entity.vo.ResultVO;
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
    public ResultVO insertArticle(@Validated @RequestBody Article article) {
        articleService.insertArticle(article);

        return ResultVO.success();
    }

    /**
     * 分页获取文章
     * 权限：无
     */
    @GetMapping("/page")
    public ResultVO page() {
        return ResultVO.success();
    }



}
