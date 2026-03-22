package org.example.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.example.fallback.SentinelFallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类说明：测试权限规则控制器
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/3/22 14:52
 */
@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    // http://localhost:14301/api/v1/auth/whiteAuth?role=11
    @GetMapping("whiteAuth")
    @SentinelResource(value = "whiteAuth",fallbackClass = SentinelFallback.class,fallback = "fallback")
    public String whiteAuth(@RequestParam("role") String role) {
        return "访问成功，你的角色是："+role;
    }

    // http://localhost:14301/api/v1/auth/blackAuth?role=uuu
    // http://localhost:14301/api/v1/auth/blackAuth?role=jxh
    @GetMapping("blackAuth")
    @SentinelResource(value = "blackAuth",fallbackClass = SentinelFallback.class,fallback = "fallback")
    public String blackAuth(@RequestParam("role") String role) {
        return "访问成功，你的角色是："+role;
    }
}
