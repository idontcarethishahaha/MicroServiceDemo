package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 类说明：商品信息控制类
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/3/15 15:02
 */

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    // http://localhost:14201/api/v1/product/select/1
    // http://localhost:14201/api/v1/product/select/2
    // http://localhost:14201/api/v1/product/select/666
    @GetMapping("select/{pid}")
    public Map select(@PathVariable("pid") Integer pid) {
        switch (pid) {
            case 1: return Map.of("name","华为Mate60","price",5899);
            case 2: return Map.of("name","小米17","price",4899);
            case 3: return Map.of("name","苹果17","price",10899);
            default: return Map.of("name","没有查询到相关商品");
        }
    }
}
