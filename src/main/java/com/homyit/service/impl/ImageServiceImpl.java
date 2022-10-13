package com.homyit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.homyit.entity.Image;
import com.homyit.enums.FileTypeEnum;
import com.homyit.service.ImageService;
import com.homyit.mapper.ImageMapper;
import com.homyit.utils.FtpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
* @author charon
* @description 针对表【image】的数据库操作Service实现
* @createDate 2022-10-12 11:08:16
*/
@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image>
    implements ImageService{

    @Resource
    private ImageMapper imageMapper;

    @Value("${nginx-config.baseurl}")
    private String baseurl;

    @Override
    public void upload(MultipartFile file, Long articleId) throws IOException {
        //file是一个临时文件，需要转存到指定位置，否则本次请求完成后临时文件会删除
        //获取原始文件名
        String originalFilename = file.getOriginalFilename();
        //分割出后缀名
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        //使用UUID重新生成文件名，防止文件名称重复造成覆盖
        String fileName = UUID.randomUUID() + suffix;

        //上传文件到ftp服务器
        FtpUtil.uploadFile(fileName, file.getInputStream(), FileTypeEnum.IS_IMAGE);

        //文件信息保存到数据库
        Image image = new Image();
        image.setArticleId(articleId);
        image.setUrl(baseurl + fileName);
        imageMapper.insert(image);
    }



    @Override
    public void upload(List<MultipartFile> files, Long articleId) throws IOException {
        for (MultipartFile file : files) {
            this.upload(file, articleId);
        }
    }

    @Override
    public List<Image> getImages(Long articleId) {
        LambdaQueryWrapper<Image> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Image::getArticleId, articleId);

        return imageMapper.selectList(queryWrapper);
    }
}




