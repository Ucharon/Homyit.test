package com.homyit.service;

import com.homyit.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author charon
* @description 针对表【user(用户表

)】的数据库操作Service
* @createDate 2022-10-05 16:06:30
*/
public interface UserService extends IService<User> {

    Map<String, String> login(User user);

    void logout();
}
