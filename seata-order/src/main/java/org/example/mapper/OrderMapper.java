package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.example.entity.Order;

/**
 * 接口说明：订单Mapper
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/4/16 18:20
 */
public interface OrderMapper {

    @Insert("""
    insert into order(sn,product_id,stock,number)
    values (#{sn},#{productId},#{number})
    """)
    int insert(Order order);
}
