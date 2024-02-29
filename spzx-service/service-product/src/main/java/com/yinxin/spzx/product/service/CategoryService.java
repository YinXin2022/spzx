package com.yinxin.spzx.product.service;

import com.yinxin.spzx.model.entity.product.Category;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-02-28 15:26
 */
@Service
public interface CategoryService {
    List<Category> findOneCategory();

    List<Category> findCategoryTree();
}
