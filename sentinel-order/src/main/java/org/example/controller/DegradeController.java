package org.example.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.SneakyThrows;
import org.example.fallback.SentinelFallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 类说明：测试降级规则的控制器
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/3/22 11:21
 */
@RestController
@RequestMapping("api/v1/degrade")
public class DegradeController {
    final Random random = new Random();
    // 慢调用，最大返回时间 RT
    @GetMapping("/rt")
    @SentinelResource(value="rt",fallbackClass = SentinelFallback.class,fallback="fallback")
    @SneakyThrows
    public String rt() {
        // 生成随机bool值，如果是真当前线程就睡眠1s，那么随机的慢调用比例将达到0.5，触发熔断规则
        if (random.nextBoolean()) {
            TimeUnit.SECONDS.sleep(1);}
        return "访问RT资源成功";
    }

    @GetMapping("/ex")
    @SentinelResource(value="ex",fallbackClass = SentinelFallback.class,fallback="fallback")
    public String ex() {
        // 生成随机bool值，如果是真当前线程就模拟一次异常，那么随机的慢调用比例将达到0.5，触发熔断规则
        if (random.nextBoolean()) throw new RuntimeException("模拟程序出错了~");
        return "访问EX资源成功";
    }

}
