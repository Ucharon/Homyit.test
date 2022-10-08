package com.homyit;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.homyit.entity.User;
import com.homyit.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@SpringBootTest
public class MapperTest {

    @Resource
    private UserMapper userMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Test
    void loginTest() {
        User user = new User();
        user.setUserName("charon");
        user.setPassword("1234");
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, user.getUserName());
        User user1 = userMapper.selectOne(queryWrapper);
        if (passwordEncoder.matches(user.getPassword(), user1.getPassword())) {
            System.out.println("ok");
        } else {
            System.out.println("error");
        }
    }

    @Test
    void TestBCryptPasswordEncoder() {
        String encode = passwordEncoder.encode("1234");
        System.out.println(encode);
    }
}
