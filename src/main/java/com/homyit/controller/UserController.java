package com.homyit.controller;

import com.homyit.annotation.SystemLog;
import com.homyit.entity.User;
import com.homyit.entity.vo.ResultVo;
import com.homyit.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    @SystemLog(businessName = "用户登录")
    public ResultVo<Map<String, String>> login(@RequestBody @Validated User user) {
        Map<String, String> map = userService.login(user);
        return ResultVo.success(map);
    }

    @GetMapping("/logout")
    public ResultVo logout() {
        userService.logout();
        return ResultVo.success();
    }


}
