package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.entity.Product;

/**
 * 接口说明：商品Mapper接口
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/4/12 16:13
 */
@Mapper
public interface ProductMapper {
    // for update 独占锁，行锁  并发性下降，建议用乐观锁
    @Select("select * from product where id = #{param1} for update")
    Product selectById(Long id);

    @Update("update product set stock = #{param2} where id = #{param1}")
    int updateStock(Long id,int newStock);
}
