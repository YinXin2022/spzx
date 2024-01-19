package com.yinxin.spzx.manager.mapper;

import com.yinxin.spzx.model.entity.product.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-01-19 13:31
 */
@Mapper
public interface SysBrandMapper {

    List<Brand> findAll();

    void save(Brand brand);

    void update(Brand brand);

    void deleteById(Long id);

    Brand findById(Long id);
}
