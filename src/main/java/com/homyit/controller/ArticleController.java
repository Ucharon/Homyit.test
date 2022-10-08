package com.homyit.controller;

import com.homyit.entity.vo.ResultVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文章相关的接口
 */
@RestController
@RequestMapping("/article")
public class ArticleController {


    /**
     * 添加文章
     * 权限：publish_article
     * @return
     */
    @PreAuthorize("hasAuthority('publish_article')")
    @PostMapping
    public ResultVO save() {
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
