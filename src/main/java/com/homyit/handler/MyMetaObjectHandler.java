package com.homyit.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.homyit.entity.DO.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {


    /**
     * 插入操作自动填充
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("公共字段自动填充[insert]...");
        log.info(metaObject.toString());
        //获取用户
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());
        List<String> setterNames = Arrays.asList(metaObject.getSetterNames());
        if (setterNames.contains("createBy")) {
            metaObject.setValue("createBy", loginUser.getUser().getId());
        }
        if (setterNames.contains("updateBy")) {
            metaObject.setValue("updateBy", loginUser.getUser().getId());
        }
    }

    /**
     * 更新操作自动填充
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("公共字段自动填充[update]...");
        log.info(metaObject.toString());
        //获取用户
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("updateUser", loginUser.getUser().getId());
    }
}
