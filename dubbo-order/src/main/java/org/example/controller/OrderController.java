package org.example.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.example.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 类说明：订单控制器
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/3/15 16:34
 */
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {//常用的4个负载均衡策略
    @DubboReference(version="1.0.0",loadbalance = "roundrobin")//加权轮询策略
    private ProductService productService;

  // http://localhost:14207/api/v1/order/select/1
    @GetMapping("select/{id}")
    public Map select(@PathVariable("id") Long id){
        // 假设当前订单的商品有两部手机
        List productList = new ArrayList<>();
        if(id==1L){
            productList.add(productService.select(1L));
            productList.add(productService.select(2L));
        }
        if(id==2L){
            productList.add(productService.select(1L));
            productList.add(productService.select(3L));
        }
        return Map.of("orderId",id,"productList",productList);
    }
}
