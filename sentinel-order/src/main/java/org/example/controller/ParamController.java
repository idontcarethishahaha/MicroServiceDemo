package org.example.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.example.fallback.SentinelFallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类说明：测试参数限流异常
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/3/22 14:12
 */
@RestController
@RequestMapping("/api/v1/param")
public class ParamController {
    // http://localhost:14301/api/v1/param/test?name=wuwenjin&age=21
    @GetMapping("test")
    @SentinelResource(value="test",fallbackClass = SentinelFallback.class,fallback="paramFallback")
    public String test(@RequestParam("name") String name,@RequestParam("age") Integer age) {
        return "访问成功："+name+","+age;
    }
}
