package com.homyit.controller;


import com.homyit.entity.Article;
import com.homyit.entity.Image;
import com.homyit.entity.vo.ResultVo;
import com.homyit.service.ImageService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

@Validated
@RestController
@RequestMapping("/image")
public class ImageController {

    @Resource
    private ImageService imageService;

    /**
     * 图片上传
     * @param file
     * @param articleId
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public ResultVo upload(@RequestPart("file") MultipartFile file, @NotNull(message = "id不能为空") Long articleId) throws IOException {
        imageService.upload(file, articleId);
        return ResultVo.success();
    }

    /**
     * 批量图片上传
     * @param files
     * @param articleId
     * @return
     * @throws IOException
     */
    @PostMapping("/uploadImages")
    public ResultVo uploadImages(@RequestPart("files") List<MultipartFile> files, @NotNull(message = "id不能为空") Long articleId) throws IOException {
        imageService.upload(files, articleId);
        return ResultVo.success();
    }

    /**
     * 根据文章id查找图片
     * @param articleId
     * @return
     */
    @GetMapping("/{articleId}")
    public ResultVo<List<Image>> getImages(@PathVariable @NotNull(message = "id不能为空") Long articleId) {
        List<Image> list = imageService.getImages(articleId);
        return ResultVo.success(list);
    }

}
