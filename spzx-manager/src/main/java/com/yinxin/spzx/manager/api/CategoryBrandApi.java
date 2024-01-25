package com.yinxin.spzx.manager.api;

import com.github.pagehelper.PageInfo;
import com.yinxin.spzx.manager.service.SysCategoryBrandService;
import com.yinxin.spzx.model.dto.product.CategoryBrandDto;
import com.yinxin.spzx.model.entity.product.Brand;
import com.yinxin.spzx.model.entity.product.CategoryBrand;
import com.yinxin.spzx.model.vo.common.Result;
import com.yinxin.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author YinXin
 * @date 2024-01-25 16:37
 */
@Tag(name = "分类品牌接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys/categoryBrand")
public class CategoryBrandApi {
    private final SysCategoryBrandService sysCategoryBrandService;

    @Operation(summary = "获取分类品牌列表")
    @GetMapping("/page/{pageNum}/{pageSize}")
    public Result<PageInfo<CategoryBrand>> pageBy(@PathVariable Integer pageNum, @PathVariable Integer pageSize, CategoryBrandDto categoryBrandDto) {
        return Result.build(sysCategoryBrandService.page(pageNum, pageSize, categoryBrandDto), ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "创建分类品牌")
    @PostMapping("/create")
    public Result createBy(@RequestBody CategoryBrand categoryBrand) {
        sysCategoryBrandService.create(categoryBrand);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "修改分类品牌")
    @PutMapping("/update")
    public Result updateBy(@RequestBody CategoryBrand categoryBrand) {
        sysCategoryBrandService.update(categoryBrand);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "删除分类品牌")
    @DeleteMapping("/delete/{id}")
    public Result deleteBy(@PathVariable Long id) {
        sysCategoryBrandService.delete(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
