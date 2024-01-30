package com.yinxin.spzx.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinxin.spzx.manager.mapper.SysProductSpecMapper;
import com.yinxin.spzx.manager.service.SysProductSpecService;
import com.yinxin.spzx.model.entity.product.ProductSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author YinXin
 * @date 2024-01-25 17:30
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SysProductSpecServiceImpl implements SysProductSpecService {
    private final SysProductSpecMapper sysProductSpecMapper;

    @Override
    public PageInfo<ProductSpec> page(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(sysProductSpecMapper.pageAll(pageNum, pageSize));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(ProductSpec productSpec) {
        sysProductSpecMapper.save(productSpec);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ProductSpec productSpec) {
        sysProductSpecMapper.update(productSpec);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        sysProductSpecMapper.delete(id);
    }
}
