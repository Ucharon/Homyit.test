package com.homyit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.homyit.entity.Article;
import com.homyit.entity.LoginUser;
import com.homyit.service.ArticleService;
import com.homyit.mapper.ArticleMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author charon
* @description 针对表【article】的数据库操作Service实现
* @createDate 2022-10-08 21:06:08
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{

    @Resource
    ArticleMapper articleMapper;

    @Override
    public void insertArticle(Article article) {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        article.setUserId(loginUser.getUser().getId());
        articleMapper.insert(article);
    }
}




