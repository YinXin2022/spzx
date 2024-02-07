package com.yinxin.spzx.manager.mapper;

import com.yinxin.spzx.model.dto.product.CategoryBrandDto;
import com.yinxin.spzx.model.entity.product.Brand;
import com.yinxin.spzx.model.entity.product.CategoryBrand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-01-25 16:39
 */
@Mapper
public interface SysCategoryBrandMapper {

    List<CategoryBrand> findAllBy(CategoryBrandDto categoryBrandDto);

    void save(CategoryBrand categoryBrand);

    void update(CategoryBrand categoryBrand);

    void delete(Long id);

    List<Brand> findBrandByCategoryId(Long categoryId);
}
