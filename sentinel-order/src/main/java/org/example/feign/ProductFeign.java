package org.example.feign;

import org.example.feign.fallback.ProductFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

/**
 * 接口说明：商品服务远程调用接口
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/3/22 15:19
 */
@FeignClient(name="sentinel-product",fallback= ProductFeignFallback.class)
public interface ProductFeign {
    @GetMapping("/api/v1/product/select/{pid}")
    public Map select(@PathVariable("pid") Integer pid);
}
