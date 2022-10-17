package com.homyit;

import com.alibaba.excel.EasyExcel;
import com.homyit.entity.DO.User;
import com.homyit.listener.StudentListener;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ExcelTest {

    /**
     * 导入
     */
    @Test
    void test01() {
//        EasyExcel.read("/Users/charon/Documents/user.xlsx",
//                User.class, new StudentListener()).sheet().doRead();
    }

    @Test
    void test02() {
        EasyExcel.write("/Users/charon/Documents/user-write.xlsx",
                User.class).sheet().doWrite(initData());
    }

    private static List<User> initData() {
        List<User> users = new ArrayList<>();
        User user = new User();
        for (int i = 0; i < 10; i++) {
            user.setId(i + 114514L);
            user.setUserName("tiansuohaoer" + i);
            user.setPassword("1919810");
            user.setStatus("1");
            users.add(user);
        }
        return users;
    }
}
