package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 类说明：订单实体类
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/4/12 15:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {
    private Long id;
    private String sn;//订单编号
    private Long productId;//商品id
    private Integer number;//商品数量
}
