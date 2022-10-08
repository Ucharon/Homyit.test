package com.homyit.mapper;

import com.homyit.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author charon
* @description 针对表【menu】的数据库操作Mapper
* @createDate 2022-10-05 16:06:30
* @Entity com.homyit.entity.Menu
*/
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long id);
}




