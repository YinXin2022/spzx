package com.yinxin.spzx.manager.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinxin.common.utils.MinIoUtil;
import com.yinxin.spzx.manager.mapper.SysBrandMapper;
import com.yinxin.spzx.manager.service.SysBrandService;
import com.yinxin.spzx.model.entity.product.Brand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author YinXin
 * @date 2024-01-19 13:28
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SysBrandServiceImpl implements SysBrandService {
    private final SysBrandMapper sysBrandMapper;


    @Override
    public PageInfo<Brand> page(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(sysBrandMapper.findAll());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Brand brand) {
        sysBrandMapper.save(brand);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Brand brand) {
        Brand b = sysBrandMapper.findById(brand.getId());
        sysBrandMapper.update(brand);
        if (!StrUtil.isEmpty(brand.getLogo())) {
            MinIoUtil.removeFile(b.getLogo());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        Brand b = sysBrandMapper.findById(id);
        sysBrandMapper.deleteById(id);
        MinIoUtil.removeFile(b.getLogo());
    }
}
