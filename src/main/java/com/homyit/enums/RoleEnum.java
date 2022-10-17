package com.homyit.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.Getter;

@Getter
public enum RoleEnum implements IEnum<Integer> {

    STUDENT(1,"学生"),

    TEACHER(2,"老师"),

    ADMIN(3,"管理员");

    private final Integer value;
    private final String desc;

    RoleEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
