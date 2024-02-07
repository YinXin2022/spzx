package com.yinxin.spzx.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinxin.spzx.manager.mapper.SysCategoryBrandMapper;
import com.yinxin.spzx.manager.service.SysCategoryBrandService;
import com.yinxin.spzx.model.dto.product.CategoryBrandDto;
import com.yinxin.spzx.model.entity.product.Brand;
import com.yinxin.spzx.model.entity.product.CategoryBrand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-01-25 16:38
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SysCategoryBrandServiceImpl implements SysCategoryBrandService {
    private final SysCategoryBrandMapper sysCategoryBrandMapper;

    @Override
    public PageInfo<CategoryBrand> page(Integer pageNum, Integer pageSize, CategoryBrandDto categoryBrandDto) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(sysCategoryBrandMapper.findAllBy(categoryBrandDto));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(CategoryBrand categoryBrand) {
        sysCategoryBrandMapper.save(categoryBrand);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(CategoryBrand categoryBrand) {
        sysCategoryBrandMapper.update(categoryBrand);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        sysCategoryBrandMapper.delete(id);
    }

    @Override
    public List<Brand> findBrandByCategoryId(Long categoryId) {
        return sysCategoryBrandMapper.findBrandByCategoryId(categoryId);
    }
}
