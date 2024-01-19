package com.yinxin.spzx.manager.service.impl;

import com.alibaba.excel.EasyExcel;
import com.yinxin.common.exception.AppException;
import com.yinxin.common.listener.ExcelListener;
import com.yinxin.spzx.manager.mapper.SysCategoryMapper;
import com.yinxin.spzx.manager.service.SysCategoryService;
import com.yinxin.spzx.model.entity.product.Category;
import com.yinxin.spzx.model.vo.common.ResultCodeEnum;
import com.yinxin.spzx.model.vo.product.CategoryExcelVo;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author YinXin
 * @date 2024-01-19 10:56
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SysCategoryServiceImpl implements SysCategoryService {
    private final SysCategoryMapper categoryMapper;

    @Override
    public List<Category> queryByParentId(Long parentId) {
        List<Category> categories = categoryMapper.findByParentId(parentId);

        if (!CollectionUtils.isEmpty(categories)) {
            categories.forEach(item -> {
                int count = categoryMapper.countByParentId(item.getId());
                item.setHasChildren(count > 0);
            });
        }

        return categories;
    }

    @Override
    public void export(HttpServletResponse response) {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode("分类数据", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            //response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            List<CategoryExcelVo> categoryList = categoryMapper.findAll().stream().map(CategoryExcelVo::entity2ExcelVo).collect(Collectors.toList());
            EasyExcel.write(response.getOutputStream(), CategoryExcelVo.class).sheet("分类数据").doWrite(categoryList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importData(MultipartFile file) {
        try {
            //创建监听器对象，传递mapper对象
            ExcelListener<CategoryExcelVo> excelListener = new ExcelListener<>(this::saveBatch);
            //调用read方法读取excel数据
            EasyExcel.read(file.getInputStream(),
                    CategoryExcelVo.class,
                    excelListener).sheet().doRead();
        } catch (IOException e) {
            throw new AppException(ResultCodeEnum.DATA_ERROR);
        }
    }

    public void saveBatch(List<CategoryExcelVo> categoryExcelVos) {
        List<Category> categoryList = categoryExcelVos.stream().map(CategoryExcelVo::excelVo2Entity).collect(Collectors.toList());
        categoryMapper.saveBatch(categoryList);
    }
}
