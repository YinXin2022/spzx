package com.yinxin.spzx.model.vo.product;

import com.alibaba.excel.annotation.ExcelProperty;
import com.yinxin.spzx.model.entity.product.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryExcelVo {

    @ExcelProperty(value = "id", index = 0)
    private Long id;

    @ExcelProperty(value = "名称", index = 1)
    private String name;

    @ExcelProperty(value = "图片url", index = 2)
    private String imageUrl;

    @ExcelProperty(value = "上级id", index = 3)
    private Long parentId;

    @ExcelProperty(value = "状态", index = 4)
    private Integer status;

    @ExcelProperty(value = "排序", index = 5)
    private Integer orderNum;

    public static CategoryExcelVo entity2ExcelVo(Category entity) {
        CategoryExcelVo categoryExcelVo = new CategoryExcelVo();
        BeanUtils.copyProperties(entity, categoryExcelVo, CategoryExcelVo.class);
        return categoryExcelVo;
    }

    public Category excelVo2Entity() {
        Category entity = new Category();
        BeanUtils.copyProperties(this, entity);
        return entity;
    }
}
