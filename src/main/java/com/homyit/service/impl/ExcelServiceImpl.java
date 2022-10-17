package com.homyit.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.homyit.entity.DO.User;
import com.homyit.entity.DO.UserRole;
import com.homyit.entity.DO.excelDo.UserExcelDo;
import com.homyit.enums.ExceptionCodeEnum;
import com.homyit.enums.RoleEnum;
import com.homyit.exception.BizException;
import com.homyit.listener.StudentListener;
import com.homyit.mapper.UserMapper;
import com.homyit.mapper.UserRoleMapper;
import com.homyit.service.ExcelService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ExcelServiceImpl implements ExcelService {

    @Resource
    UserMapper userMapper;

    @Resource
    PasswordEncoder passwordEncoder;

    @Resource
    UserRoleMapper userRoleMapper;


    @Override
    public void uploadStudent(MultipartFile file) {
        ExcelReader reader = null;

        try {
            reader = EasyExcel.read(file.getInputStream(), UserExcelDo.class,
                    new StudentListener(userMapper, passwordEncoder, userRoleMapper)).build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(ExceptionCodeEnum.EXCEL_ERROR);
        }
        ReadSheet build = EasyExcel.readSheet(0).build();
        reader.read(build);
        reader.finish();
    }

    @Override
    public void download(HttpServletResponse response) throws IOException {
        //设置编码格式
        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/vnd.ms-excel");
        String fileName = URLEncoder.encode("学生信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename*=UTF-8''" + fileName + ".xlsx");

        //获取工作表对象
        ExcelWriterSheetBuilder sheet = EasyExcel.write(response.getOutputStream(), User.class).sheet();
        //准备数据
        //查询出所有学生的id
        List<Long> studentIds = userRoleMapper
                .selectByRole(RoleEnum.STUDENT.getValue());
        //过滤出管理员
        List<User> students = studentIds.stream().map(userMapper::selectByIdNotAdmin).collect(Collectors.toList());
        //删除空元素
        students.removeAll(Collections.singleton(null));
        sheet.doWrite(students);
    }
}
