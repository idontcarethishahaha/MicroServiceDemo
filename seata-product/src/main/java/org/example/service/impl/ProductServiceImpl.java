package org.example.service.impl;

import org.example.entity.Product;
import org.example.mapper.ProductMapper;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类说明：商品服务实现类
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/4/12 16:26
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public void updateStock(Long pid, int number) {
        Product product = productMapper.selectById(pid);
        if(product == null) {
            throw new RuntimeException("商品不存在~");
        }
        if(product.getStock()<number){
            throw new RuntimeException("商品不存在~");
        }
        // 减去购买数量，实际上应该加乐观锁
        productMapper.updateStock(pid, product.getStock()-number);
    }
}
