package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.util.Map;

/**
 * 类说明：订单控制器
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/3/22 16:23
 */
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    // http://localhost:14402/api/v1/order/select/1

    // http://localhost:14401/gateway-order-server/api/v1/order/select/1
    @GetMapping("select/{id}")
    public Map selectOrder(@PathVariable("id") Integer id) {
        return Map.of("orderId",id,"price",100);
    }
}
