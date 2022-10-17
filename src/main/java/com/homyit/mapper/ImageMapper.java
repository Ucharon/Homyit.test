package com.homyit.mapper;

import com.homyit.entity.DO.Image;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author charon
* @description 针对表【image】的数据库操作Mapper
* @createDate 2022-10-12 11:08:16
* @Entity com.homyit.entity.DO.Image
*/
@Mapper
public interface ImageMapper extends BaseMapper<Image> {

}




