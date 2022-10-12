package com.homyit.utils;

import com.homyit.enums.ExceptionCodeEnum;
import com.homyit.enums.FileTypeEnum;
import com.homyit.exception.BizException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Component
public class FtpUtil {

    //ftp服务器地址
    private static String FTP_ADDRESS;
    //端口号
    private static int FTP_PORT;
    //用户名
    private static String FTP_USERNAME;
    //密码
    private static String FTP_PASSWORD;

    public static void uploadFile(String fileName, InputStream inputStream, FileTypeEnum fileTypeEnum) {
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(FTP_ADDRESS, FTP_PORT);
            ftp.login(FTP_USERNAME, FTP_PASSWORD);
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                throw new BizException(ExceptionCodeEnum.UPLOAD_FAIL);
            }
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftp.makeDirectory(fileTypeEnum.getBasepath());
            ftp.changeWorkingDirectory(fileTypeEnum.getBasepath());
            ftp.enterLocalPassiveMode();
            ftp.storeFile(fileName, inputStream);
            inputStream.close();
            ftp.logout();
        } catch (IOException e) {
//            e.printStackTrace();
            throw new BizException(ExceptionCodeEnum.UPLOAD_FAIL);
        }
        if (ftp.isConnected()) {
            try {
                ftp.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Value("${ftp-config.address}")
    public void setFtpAddress(String ftpAddress) {
        FTP_ADDRESS = ftpAddress;
    }

    @Value("${ftp-config.port}")
    public void setFtpPort(int ftpPort) {
        FTP_PORT = ftpPort;
    }

    @Value("${ftp-config.username}")
    public void setFtpUsername(String ftpUsername) {
        FTP_USERNAME = ftpUsername;
    }

    @Value("${ftp-config.password}")
    public void setFtpPassword(String ftpPassword) {
        FTP_PASSWORD = ftpPassword;
    }

}
