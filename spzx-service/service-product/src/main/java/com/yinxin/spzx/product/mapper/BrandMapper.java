package com.yinxin.spzx.product.mapper;

import com.yinxin.spzx.model.entity.product.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-02-29 16:56
 */
@Mapper
public interface BrandMapper {
    List<Brand> findAll();
}
