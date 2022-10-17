package com.homyit.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ExcelService {

    void uploadStudent(MultipartFile file);

    void download(HttpServletResponse response) throws IOException;
}
