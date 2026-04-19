package org.example.service;

import org.example.entity.Order;

/**
 * 接口说明：订单业务接口
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/4/16 18:17
 */
public interface OrderService {
    /**
     * 添加一笔订单
     * @param order
     */
    void insert(Order order);
}
