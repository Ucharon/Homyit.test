package com.homyit;

import com.homyit.entity.User;
import com.homyit.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Map;

@SpringBootTest
public class ServiceTest {

    @Resource
    UserService userService;

    @Test
    void LoginTest() {
        User user = new User();
        user.setUserName("charon");
        user.setPassword("1234");
        Map<String, String> map = userService.login(user);
        System.out.println(map.toString());
    }
}
