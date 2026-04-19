package org.example.service.impl;

import io.seata.spring.annotation.GlobalTransactional;
import org.example.entity.Order;
import org.example.feign.ProductFeign;
import org.example.mapper.OrderMapper;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * 类说明：订单业务的实现类
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/4/16 18:18
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;//执行本地数据库操作创建订单记录

    @Autowired
    private ProductFeign productFeign;//调用远程接口扣库存

    // ctrl + i
    @Override
    //全局事务注解
    @GlobalTransactional(rollbackFor = Exception.class)
    @Transactional(rollbackFor = Exception.class)//本地事务,有全局的可以不加
    public void insert(Order order) {
       //1 随机生成一个sn编码(可以用雪花算法生成唯一id,这里是随意写的
        order.setSn(UUID.randomUUID().toString());
        if(orderMapper.insert(order) == 0){//说明订单创建失败
            throw new RuntimeException("创建订单失败~");
        }
        //2 扣减库存
        if(!productFeign.updateStock(order.getProductId(), order.getNumber()).get("code").equals(200)){
            throw new RuntimeException("库存扣减失败~");
        }
    }
}
//分布式事务中间件seata
