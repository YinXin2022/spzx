package com.yinxin.spzx.product.api;

import com.yinxin.spzx.model.entity.product.Category;
import com.yinxin.spzx.model.vo.common.Result;
import com.yinxin.spzx.model.vo.common.ResultCodeEnum;
import com.yinxin.spzx.product.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-02-28 16:21
 */
@Tag(name = "分类接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product/category")
public class CategoryApi {
    private final CategoryService categoryService;

    @Operation(summary = "获取分类树形数据")
    @GetMapping("findCategoryTree")
    public Result<List<Category>> findCategoryTree(){
        List<Category> list = categoryService.findCategoryTree();
        return Result.build(list,  ResultCodeEnum.SUCCESS);
    }
}
