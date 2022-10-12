package com.homyit.controller;


import com.homyit.entity.vo.ResultVo;
import com.homyit.service.ImageService;
import com.homyit.utils.FtpUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Resource
    private ImageService imageService;

    @PostMapping("/upload")
    public ResultVo upload(@RequestPart("file") MultipartFile file, Long articleId) throws IOException {
        imageService.upload(file, articleId);
        return ResultVo.success();
    }



}
