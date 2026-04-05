package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 类说明：订单消息类
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/4/5 11:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
// 对象序列化需要实现这个接口，否则会报错
public class OrderMessage implements Serializable {
    private Long id;
    private String sn;//订单编号
    private Float price;//订单金额
    private String phone;//联系电话
    private String username;//下单用户
}
