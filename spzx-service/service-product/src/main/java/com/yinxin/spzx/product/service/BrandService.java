package com.yinxin.spzx.product.service;

import com.yinxin.spzx.model.entity.product.Brand;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-02-29 16:57
 */
@Service
public interface BrandService {
    List<Brand> findAll();

}
