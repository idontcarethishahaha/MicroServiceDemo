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
@RequestMapping("/api/v1/flow")
public class FlowController {
    // 流控规则 - 直接限制
    @GetMapping("qpsDirect")
    // 用来定义Sentinel中的资源
    @SentinelResource(value="qpsDirect",fallbackClass= SentinelFallback.class,fallback = "fallback")
    public String qpsDirect() {
        return "访问成功!";//正常的访问结果
    }

    @GetMapping("qpsDirectWarmUp")
    @SentinelResource(value = "qpsDirectWarmUp",fallbackClass = SentinelFallback.class,fallback="fallback")
    public String qpsDirectWarmUp() {
        return "访问成功2!";//正常的返回结果
    }

    @GetMapping("qpsDirectLineUp")
    @SentinelResource(value = "qpsDirectLineUp",fallbackClass = SentinelFallback.class,fallback="fallback")
    public String qpsDirectLineUp() {
        return "访问成功3!";//正常的返回结果
    }
    // 资源1和资源2是两个关联的资源，当资源2访问量超过QPS时，对1进行流量控制

    @GetMapping("qsRelation1")
    @SentinelResource(value = "wwj",fallbackClass = SentinelFallback.class,fallback="fallback")
    public String qsRelation1() {
        return "吴文瑾进货成功!";//正常的返回结果
    }

    @GetMapping("qsRelation2")
    @SentinelResource(value = "sjx",fallbackClass = SentinelFallback.class,fallback="fallback")
    public String qsRelation2() {
        return "孙佳欣出货成功!";//正常的返回结果
    }

    /** 当资源 threadDirect 降级时，执行 SentinelFallback 类的 fallback() 方法 */
    @GetMapping("/threadDirect")
    @SentinelResource(value = "threadDirect", fallbackClass = SentinelFallback.class, fallback = "fallback")
    public String threadDirect() {
        return "资源threadDirect用成功";
    }

}
