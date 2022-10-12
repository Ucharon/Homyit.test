package com.homyit.enums;

import lombok.Getter;

@Getter
public enum FileTypeEnum {
    /**
     * 这个是图片
     */
    IS_IMAGE(0, "images/"),

    /**
     * 这个是文件
     */
    IS_FILE(1, "files/");

    //图片或者文件的代码
    private int code;

    //对应的basepath
    private String basepath;

    FileTypeEnum(int code, String basepath) {
        this.code = code;
        this.basepath = basepath;
    }
}
