package com.homyit.controller;


import com.homyit.annotation.SystemLog;
import com.homyit.entity.VO.ResultVo;
import com.homyit.service.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/excel")
@PreAuthorize("hasAuthority('managing_user')")
public class ExcelController {

    @Resource
    ExcelService excelService;

    /**
     * 上传学生信息
     * @param file
     * @return
     */
    @PostMapping("/uploadStudent")
    @SystemLog(businessName = "通过excel上传学生信息")
    public ResultVo upload(@RequestPart("file") MultipartFile file) {
        excelService.uploadStudent(file);

        return ResultVo.success();
    }

    @GetMapping("/downloadStudent")
    @SystemLog(businessName = "获取学生信息excel表格")
    public ResultVo download(HttpServletResponse response) throws IOException {
        excelService.download(response);

        return ResultVo.success();
    }


}
