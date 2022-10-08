package com.homyit.controller;

import com.homyit.entity.User;
import com.homyit.entity.vo.ResultVO;
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
    public ResultVO<Map<String, String>> login(@RequestBody @Validated User user) {
        Map<String, String> map = userService.login(user);
        return ResultVO.success(map);
    }

    @GetMapping("/logout")
    public ResultVO logout() {
        userService.logout();
        return ResultVO.success();
    }


}
