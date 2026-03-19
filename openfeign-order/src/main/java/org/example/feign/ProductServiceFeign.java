package org.example.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

/**
 * 接口说明：商品服务的远程的调用接口
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/3/15 15:09
 */

@FeignClient(name="openfeign-product")
public interface ProductServiceFeign {
    @GetMapping("/api/v1/product/select/{pid}")
    public Map select(@PathVariable("pid") Integer pid);
}

