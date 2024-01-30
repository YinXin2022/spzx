package com.yinxin.spzx.manager.api;

import com.github.pagehelper.PageInfo;
import com.yinxin.spzx.manager.service.SysProductSpecService;
import com.yinxin.spzx.model.entity.product.ProductSpec;
import com.yinxin.spzx.model.vo.common.Result;
import com.yinxin.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author YinXin
 * @date 2024-01-25 17:29
 */
@Tag(name = "商品规格接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys/productSpec")
public class ProductSpecApi {
    private final SysProductSpecService sysProductSpecService;

    @Operation(summary = "获取商品规格列表")
    @GetMapping("/page/{pageNum}/{pageSize}")
    public Result<PageInfo<ProductSpec>> pageBy(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        return Result.build(sysProductSpecService.page(pageNum, pageSize), ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "创建商品规格")
    @PostMapping("/create")
    public Result createBy(@RequestBody ProductSpec productSpec) {
        sysProductSpecService.create(productSpec);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "修改商品规格")
    @PutMapping("/update")
    public Result updateBy(@RequestBody ProductSpec productSpec) {
        sysProductSpecService.update(productSpec);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "删除商品规格")
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Long id) {
        sysProductSpecService.delete(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
