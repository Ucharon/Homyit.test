package com.homyit.mapper;

import com.homyit.entity.DO.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author charon
* @description 针对表【user_role】的数据库操作Mapper
* @createDate 2022-10-17 10:57:44
* @Entity com.homyit.entity.DO.UserRole
*/
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

    List<Long> selectByRole(Integer roleId);
}




