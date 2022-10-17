package com.homyit.mapper;

import com.homyit.entity.DO.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author charon
* @description 针对表【user(用户表

)】的数据库操作Mapper
* @createDate 2022-10-05 16:06:30
* @Entity com.homyit.entity.DO.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

    User selectByIdNotAdmin(Long id);
}




