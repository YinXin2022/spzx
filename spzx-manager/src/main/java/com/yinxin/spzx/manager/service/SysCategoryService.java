package com.yinxin.spzx.manager.service;

import com.yinxin.spzx.model.entity.product.Category;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-01-19 10:56
 */
@Service
public interface SysCategoryService {
    List<Category> queryByParentId(Long parentId);

    void export(HttpServletResponse response);

    void importData(MultipartFile file);
}
