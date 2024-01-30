package com.yinxin.spzx.manager.api;

import com.github.pagehelper.PageInfo;
import com.yinxin.spzx.manager.service.SysBrandService;
import com.yinxin.spzx.model.entity.product.Brand;
import com.yinxin.spzx.model.filter.BrandFilter;
import com.yinxin.spzx.model.vo.common.Result;
import com.yinxin.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-01-19 13:27
 */
@Tag(name = "品牌管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys/brand")
public class BrandApi {
    private final SysBrandService sysBrandService;

    @Operation(summary = "获取品牌列表")
    @PostMapping("/page/{pageNum}/{pageSize}")
    public Result<PageInfo<Brand>> pageBy(@PathVariable Integer pageNum, @PathVariable Integer pageSize, @RequestBody BrandFilter filter) {
        return Result.build(sysBrandService.page(pageNum, pageSize, filter), ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "创建品牌")
    @PostMapping("/create")
    public Result createBy(@RequestBody Brand brand) {
        sysBrandService.create(brand);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "修改品牌")
    @PutMapping("/update")
    public Result updateBy(@RequestBody Brand brand) {
        sysBrandService.update(brand);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "删除品牌")
    @DeleteMapping("/delete/{id}")
    public Result deleteBy(@PathVariable Long id) {
        sysBrandService.delete(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
