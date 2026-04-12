package org.example.service;

/**
 * 接口说明：商品服务接口
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/4/12 16:21
 */

public interface ProductService {
    /**
     * 扣减商品库存的方法
     * @param pid 商品ID
     * @param number 购买数量
     */
    void updateStock(Long pid,int number);
}

