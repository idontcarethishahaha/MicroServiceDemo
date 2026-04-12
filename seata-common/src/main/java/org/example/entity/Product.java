package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 类说明：产品实体类
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/4/12 15:51
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
    private Long id;
    private String title;//商品标题
    private Integer stock;//商品库存
}
