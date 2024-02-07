package com.yinxin.spzx.manager.service.impl;

import com.yinxin.spzx.manager.mapper.SysProductUnitMapper;
import com.yinxin.spzx.manager.service.SysProductUnitService;
import com.yinxin.spzx.model.entity.base.ProductUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-01-30 17:41
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SysProductUnitServiceImpl implements SysProductUnitService {
    private final SysProductUnitMapper sysProductUnitMapper;

    @Override
    public List<ProductUnit> all() {
        return sysProductUnitMapper.findAll();
    }
}
