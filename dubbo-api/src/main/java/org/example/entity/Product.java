package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 类说明：商品实体类
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/3/15 15:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
    private Long pid;
    private String name;
    // 其他属性省略
}
