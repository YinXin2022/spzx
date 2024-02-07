package com.yinxin.spzx.manager.mapper;

import com.yinxin.spzx.model.entity.product.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-01-30 16:41
 */
@Mapper
public interface SysProductMapper {

    List<Product> findAll();

    void save(Product product);

    Product findById(Long id);

    void updateById(Product product);

    void deleteById(Long id);
}
