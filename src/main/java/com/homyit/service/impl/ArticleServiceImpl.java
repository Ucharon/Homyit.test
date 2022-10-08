package com.homyit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.homyit.entity.Article;
import com.homyit.service.ArticleService;
import com.homyit.mapper.ArticleMapper;
import org.springframework.stereotype.Service;

/**
* @author charon
* @description 针对表【article】的数据库操作Service实现
* @createDate 2022-10-08 21:06:08
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{

}




