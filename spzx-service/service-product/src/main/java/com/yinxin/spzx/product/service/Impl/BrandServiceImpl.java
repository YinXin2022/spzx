package com.yinxin.spzx.product.service.Impl;

import com.yinxin.spzx.model.entity.product.Brand;
import com.yinxin.spzx.product.mapper.BrandMapper;
import com.yinxin.spzx.product.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-02-29 16:57
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandMapper brandMapper;

    @Override
    public List<Brand> findAll() {
        return brandMapper.findAll();
    }
}
