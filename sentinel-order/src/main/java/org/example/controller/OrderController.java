package org.example.controller;

import org.example.feign.ProductFeign;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @date 2026/3/15 15:14
 */
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    @Autowired
    private ProductFeign productServiceFeign;

    //  http://localhost:14301/api/v1/order/select/1
    // 查询订单
    @GetMapping("select/{id}")
    public Map select(@PathVariable("id") Long id){
        // 假设当前订单的商品有两部手机
        List productList = new ArrayList<>();
        if(id==1L){
            productList.add(productServiceFeign.select(1));
            productList.add(productServiceFeign.select(2));
        }
        if(id==2L){
            productList.add(productServiceFeign.select(1));
            productList.add(productServiceFeign.select(3));
        }
        return Map.of("orderId",id,"productList",productList);
    }
}
