package org.example.feign.fallback;

import org.example.feign.ProductFeign;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 类说明：商品Feign接口的回调类
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/3/22 15:31
 */
@Component
public class ProductFeignFallback implements ProductFeign {
    @Override
    public Map select(Integer pid) {
        return Map.of("code","404","msg","该服务不见了，请稍后再试~");
    }
}
