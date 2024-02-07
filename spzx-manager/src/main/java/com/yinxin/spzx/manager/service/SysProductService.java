package com.yinxin.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.yinxin.spzx.model.entity.product.Product;
import org.springframework.stereotype.Service;

/**
 * @author YinXin
 * @date 2024-01-30 16:39
 */
@Service
public interface SysProductService {
    PageInfo<Product> page(Integer pageNum, Integer pageSize);

    void create(Product product);

    Product get(Long id);

    void update(Product product);

    void delete(Long id);

    void audit(Long id, Integer status);

    void shelves(Long id, Integer status);
}
