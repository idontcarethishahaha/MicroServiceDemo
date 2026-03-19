package org.example.service;

import org.example.entity.Product;

/**
 * 接口说明：商品业务接口
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/3/15 15:56
 */
public interface ProductService {
    Product select(Long id);
}
