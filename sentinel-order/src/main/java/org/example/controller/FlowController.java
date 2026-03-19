package org.example.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.example.fallback.SentinelFallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类说明：测试Sentinel流控规则
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/3/19 20:50
 */
@RestController
@RequestMapping("/qpi/v1/flow")
public class FlowController {
    // 流控规则 - 直接限制
    @GetMapping("qpsDirect")
    // 用来定义Sentinel中的资源
    @SentinelResource(value="qpsDirect",fallbackClass= SentinelFallback.class,fallback = "fallback")
    public String qpsDirect() {
        return "访问成功!";//正常的访问结果
    }
}
