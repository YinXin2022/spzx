package com.yinxin.spzx.manager.mapper;

import com.yinxin.spzx.model.entity.base.ProductUnit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-01-30 17:42
 */
@Mapper
public interface SysProductUnitMapper {

    List<ProductUnit> findAll();
}
