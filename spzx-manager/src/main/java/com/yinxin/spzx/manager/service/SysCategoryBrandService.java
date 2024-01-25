package com.yinxin.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.yinxin.spzx.model.dto.product.CategoryBrandDto;
import com.yinxin.spzx.model.entity.product.CategoryBrand;
import org.springframework.stereotype.Service;

/**
 * @author YinXin
 * @date 2024-01-25 16:38
 */
@Service
public interface SysCategoryBrandService {
    PageInfo<CategoryBrand> page(Integer pageNum, Integer pageSize, CategoryBrandDto categoryBrandDto);

    void create(CategoryBrand categoryBrand);

    void update(CategoryBrand categoryBrand);

    void delete(Long id);
}
