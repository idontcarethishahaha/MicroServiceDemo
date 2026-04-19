package org.example.service.impl;

import io.seata.core.context.RootContext;
import io.seata.core.exception.TransactionException;
import io.seata.spring.annotation.GlobalTransactional;
import io.seata.tm.api.GlobalTransactionContext;
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
   // @GlobalTransactional
    public void updateStock(Long pid, int number) throws TransactionException {
        Product product = productMapper.selectById(pid);
        if(product == null) {
            // 手动执行回滚操作，可以不加注解
            GlobalTransactionContext.reload(RootContext.getXID()).rollback();
            throw new RuntimeException("商品不存在~");
        }
        if(product.getStock()<number){
            GlobalTransactionContext.reload(RootContext.getXID()).rollback();
            throw new RuntimeException("商品不存在~");
        }
        // 减去购买数量，实际上应该加乐观锁
        productMapper.updateStock(pid, product.getStock()-number);
    }
}
