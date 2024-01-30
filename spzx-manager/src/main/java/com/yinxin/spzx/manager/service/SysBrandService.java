package com.yinxin.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.yinxin.spzx.model.entity.product.Brand;
import com.yinxin.spzx.model.filter.BrandFilter;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-01-19 13:27
 */
@Service
public interface SysBrandService {
    PageInfo<Brand> page(Integer pageNum, Integer pageSize, BrandFilter filter);

    void create(Brand brand);

    void update(Brand brand);

    void delete(Long id);
}
