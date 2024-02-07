package com.yinxin.spzx.manager.service;

import com.yinxin.spzx.model.entity.base.ProductUnit;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-01-30 17:40
 */
@Service
public interface SysProductUnitService {
    List<ProductUnit> all();
}
