package org.example.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.*;

/**
 * 类说明：jmeter压力测试
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/3/19 19:52
 */
@Data
class User {
    private String username,password;
}
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @GetMapping("login")//请求参数在请求头，没有请求体
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
        System.out.println("用户名：" +username +"密码："+ password);
        return "登录成功";
    }

    @PostMapping("login")//请求参数在请求体
    public String login(@RequestBody User user) {
        System.out.println("用户信息："+user);
        return "登录成功";
    }
}
