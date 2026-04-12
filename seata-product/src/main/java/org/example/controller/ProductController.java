package org.example.controller;

import jakarta.annotation.Resource;
import org.example.service.ProductService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 类说明：商品控制器
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/4/12 16:44
 */
@RestController
@RequestMapping("api/v1/product")
public class ProductController {
    @Resource
    private ProductService productService;

    @PostMapping("/updateStock/{pid}/{number}")
    public Map updateStock(@PathVariable("pid") Long pid,
                           @PathVariable("number") Integer number){
        productService.updateStock(pid,number);
        return Map.of("code",200,"msg","库存扣减成功");
    }
}
