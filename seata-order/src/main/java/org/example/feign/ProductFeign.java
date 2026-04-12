package org.example.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

/**
 * 接口说明：商品服务远程调用接口
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/4/12 16:56
 */
@FeignClient(name = "seata-product")
public interface ProductFeign {
    @PostMapping("/api/v1/product/updateStock/{pid}/{number}")
    public Map updateStock(@PathVariable("pid") Long pid,
                           @PathVariable("number") Integer number);
}
