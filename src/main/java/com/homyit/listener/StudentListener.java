package com.homyit.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.homyit.entity.DO.User;
import com.homyit.entity.DO.UserRole;
import com.homyit.entity.DO.excelDo.UserExcelDo;
import com.homyit.enums.RoleEnum;
import com.homyit.mapper.UserMapper;
import com.homyit.mapper.UserRoleMapper;
import org.apache.commons.collections4.BagUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 读取文档的监听器类
 */
public class StudentListener extends AnalysisEventListener<UserExcelDo> {

    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder;
    private UserRoleMapper userRoleMapper;


    public StudentListener(UserMapper userMapper, PasswordEncoder passwordEncoder, UserRoleMapper userRoleMapper) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRoleMapper = userRoleMapper;
    }

    /**
     * 读监听器，每读一行内容，都会调用一次对象读invoke，在invoke可以操作使用读取到到数据
     *
     * @param
     * @param analysisContext
     */
    @Override
    public void invoke(UserExcelDo userExcelDo, AnalysisContext analysisContext) {
        //密码加密
        userExcelDo.setPassword(passwordEncoder.encode(userExcelDo.getPassword()));
        //拷贝对象
        User user = new User();
        BeanUtils.copyProperties(userExcelDo, user);
        userMapper.insert(user);
        //添加到用户角色表
        //这里插入后，mabatisplus会自动回填id到user
        userRoleMapper.insert(new UserRole(user.getId(), RoleEnum.STUDENT.getValue().longValue()));
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }


}
