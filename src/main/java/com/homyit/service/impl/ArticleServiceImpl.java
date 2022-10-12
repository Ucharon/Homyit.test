package com.homyit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.homyit.entity.Article;
import com.homyit.entity.LoginUser;
import com.homyit.entity.User;
import com.homyit.entity.dto.ArticlePageDto;
import com.homyit.entity.vo.ArticleVo;
import com.homyit.entity.vo.PageArticleVo;
import com.homyit.mapper.UserMapper;
import com.homyit.service.ArticleService;
import com.homyit.mapper.ArticleMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author charon
 * @description 针对表【article】的数据库操作Service实现
 * @createDate 2022-10-08 21:06:08
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
        implements ArticleService {

    @Resource
    ArticleMapper articleMapper;

    @Resource
    UserMapper userMapper;

    @Override
    public void insertArticle(Article article) {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        article.setUserId(loginUser.getUser().getId());
        articleMapper.insert(article);
    }

    /**
     * 分页查询服务
     * @param articlePageDto 客户端传来的查询参数
     * @return
     */
    @Override
    public Page<PageArticleVo> pageList(ArticlePageDto articlePageDto) {
        //构造分页构造器
        Page<Article> pageInfo = new Page<>(articlePageDto.getPage(),
                articlePageDto.getPageSize(),
                articlePageDto.getTotal());
        Page<PageArticleVo> articleVoPage = new Page<>();

        //构造条件构造器
        //查出模糊条件搜索出的用户id
        LambdaQueryWrapper<User> userQueryWrapper = new LambdaQueryWrapper<>();
        userQueryWrapper.like(articlePageDto.getUsername() != null,
                User::getNickName,
                articlePageDto.getUsername());

        //获取出用户的id，然后存入map，方便后续封装进vo对象中
        Map<Long, String> map = userMapper.selectList(userQueryWrapper).stream()
                .collect(Collectors.toMap(User::getId, User::getNickName));
        List<Long> userIds = new ArrayList<>(map.keySet());

        //查找出所有符合条件的Article，并进行分页处理
        LambdaQueryWrapper<Article> articleQueryWrapper = new LambdaQueryWrapper<>();
        articleQueryWrapper.like(articlePageDto.getTitle() != null,
                Article::getTitle,
                articlePageDto.getTitle());
        articleQueryWrapper.in(!userIds.isEmpty(), Article::getUserId, userIds);
        //先根据浏览量进行降序排列，如果浏览量相同，则根据时间倒序排列
        articleQueryWrapper.orderByDesc(Article::getViews).orderByDesc(Article::getUpdateTime);
        articleMapper.selectPage(pageInfo, articleQueryWrapper);

        //对象拷贝
        BeanUtils.copyProperties(pageInfo, articleVoPage, "records");
        List<PageArticleVo> articleVoList = pageInfo.getRecords().stream().map((item) -> {
            PageArticleVo articleVo = new PageArticleVo();
            BeanUtils.copyProperties(item, articleVo);
            //封装文章作者
            articleVo.setUsername(map.get(item.getUserId()));
            return articleVo;
        }).collect(Collectors.toList());
        articleVoPage.setRecords(articleVoList);

        return articleVoPage;
    }

    @Override
    public ArticleVo getByIdWithUsername(Long id) {
        //连表查询到username
        ArticleVo articleVo = articleMapper.getByIdWithUsername(id);

        return articleVo;
    }


}




