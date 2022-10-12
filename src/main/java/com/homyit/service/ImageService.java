package com.homyit.service;

import com.homyit.entity.Image;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
* @author charon
* @description 针对表【image】的数据库操作Service
* @createDate 2022-10-12 11:08:16
*/
public interface ImageService extends IService<Image> {

    void upload(MultipartFile file, Long articleId) throws IOException;
}
