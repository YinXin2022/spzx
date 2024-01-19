package com.yinxin.spzx.manager.mapper;

import com.yinxin.spzx.model.entity.product.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-01-19 10:57
 */
@Mapper
public interface SysCategoryMapper {

    List<Category> findByParentId(Long parentId);

    Integer countByParentId(Long parentId);

    List<Category> findAll();

    void saveBatch(List<Category> categories);
}
