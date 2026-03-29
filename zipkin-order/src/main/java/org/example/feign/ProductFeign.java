package org.example.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name="mmt-product")
public interface ProductFeign {
    @GetMapping("/api/v1/product/select/{id}")
    public Map select(@PathVariable("id") Integer id);
}
