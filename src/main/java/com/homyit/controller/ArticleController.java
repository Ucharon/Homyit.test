package com.homyit.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.homyit.annotation.SystemLog;
import com.homyit.entity.DO.Article;
import com.homyit.entity.DTO.ArticlePageDto;
import com.homyit.entity.VO.ArticleVo;
import com.homyit.entity.VO.PageArticleVo;
import com.homyit.entity.VO.ResultVo;
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
     *
     * @return
     */
    @PreAuthorize("hasAuthority('publish_article')")
    @SystemLog(businessName = "添加文章")
    @PostMapping
    public ResultVo insertArticle(@Validated @RequestBody Article article) {
        articleService.insertArticle(article);
        return ResultVo.success();
    }

    /**
     * 分页获取文章
     * 权限：read_article
     */
    @PreAuthorize("hasAuthority('read_article')")
    @SystemLog(businessName = "分页查询获取文章")
    @PostMapping("/page")
    public ResultVo<Page<PageArticleVo>> page(@RequestBody ArticlePageDto articlePageDto) {
        Page<PageArticleVo> page = articleService.pageList(articlePageDto);
        return ResultVo.success(page);
    }

    /**
     * 根据id查询单个文章的详细信息
     */
    @PreAuthorize("hasAuthority('read_article')")
    @SystemLog(businessName = "查询单个文章")
    @GetMapping("/{id}")
    public ResultVo<ArticleVo> selectOne(@PathVariable Long id) {
        ArticleVo articleVo = articleService.getByIdWithUsername(id);
        return ResultVo.success(articleVo);
    }

}
