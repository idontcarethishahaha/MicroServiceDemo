package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类说明：测试用控制器
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/3/26 18:36
 */
@RestController
@RequestMapping("/api/v1/start")
public class HelloController {
    @GetMapping("hello")
    // 断言在gateway包下的application.yml
    // gateway是网关，gateway-order是服务
    // 假设：参数a是当前接口非常关键的参数，为提供该参数或参数格式错误都讲导致访问失败
    // http://localhost:14401/gateway-order-server/api/v1/start/hello?a=6
    public String hello(@RequestParam("a") Integer a, @RequestParam("name") String name) {
        return String.format("祝贺%s访问成功，参数a = %d",name, a);
    }
}
