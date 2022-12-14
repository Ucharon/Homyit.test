package com.homyit.mapper;

import com.homyit.entity.DO.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.homyit.entity.VO.ArticleVo;
import org.apache.ibatis.annotations.Mapper;

/**
* @author charon
* @description 针对表【article】的数据库操作Mapper
* @createDate 2022-10-09 20:59:09
* @Entity com.homyit.entity.DO.Article
*/
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    ArticleVo getByIdWithUsername(Long id);
}




