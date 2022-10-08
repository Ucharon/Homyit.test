package com.homyit.mapper;

import com.homyit.domain.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author charon
* @description 针对表【menu】的数据库操作Mapper
* @createDate 2022-10-05 16:06:30
* @Entity com.homyit.domain.Menu
*/
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

}




