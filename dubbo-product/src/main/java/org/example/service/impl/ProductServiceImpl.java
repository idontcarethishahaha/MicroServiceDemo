package org.example.service.impl;

import org.apache.dubbo.config.annotation.DubboService;
import org.example.entity.Product;
import org.example.service.ProductService;

/**
 * 类说明：
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/3/15 16:15
 */
@DubboService(version="1.0.0")
public class ProductServiceImpl implements ProductService {
    @Override
    public Product select(Long id) {
        switch (id.intValue()){
            case 1: return new Product(1L,"华为Mate60");
            case 2: return new Product(2L,"小米17 Pro");
            case 3: return new Product(3L,"OPPO Reno12");
        }
        return null;
    }
}
