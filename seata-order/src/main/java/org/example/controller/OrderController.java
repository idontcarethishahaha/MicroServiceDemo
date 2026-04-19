package org.example.controller;

import org.example.entity.Order;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 类说明：订单的控制器
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/4/16 19:08
 */
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/insert/{pid}/{number}")
    public Map insert(@PathVariable("pid") Long pid,
                      @PathVariable("number") Integer number) {
       Order order = new Order(null,null,pid,number);
       orderService.insert(order);//如果创建订单未报错
       return Map.of("code",200,
               "message","创建订单成功");
    }
}
