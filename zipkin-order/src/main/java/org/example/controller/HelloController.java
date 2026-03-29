package org.example.controller;

import org.example.feign.ProductFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类说明：测试zipkin信息采集
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/3/29 10:11
 */
@RestController
@RequestMapping("/api/v1")
public class HelloController {
    @Autowired
    private ProductFeign productFeign;
    // http://localhost:14501/api/v1/hello
    @GetMapping("hello")
    public String hello(){
        return "访问成功" + productFeign.select(1);
    }
}
