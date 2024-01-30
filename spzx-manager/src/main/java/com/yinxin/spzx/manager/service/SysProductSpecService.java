package com.yinxin.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.yinxin.spzx.model.entity.product.ProductSpec;
import org.springframework.stereotype.Service;

/**
 * @author YinXin
 * @date 2024-01-25 17:30
 */
@Service
public interface SysProductSpecService {
    PageInfo<ProductSpec> page(Integer pageNum, Integer pageSize);

    void create(ProductSpec productSpec);

    void update(ProductSpec productSpec);

    void delete(Long id);
}
