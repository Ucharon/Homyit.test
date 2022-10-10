package com.homyit.entity.dto;

import lombok.Data;

@Data
public class PageDto {

    private Integer page;
    private Integer pageSize;
    private Integer total;
}
