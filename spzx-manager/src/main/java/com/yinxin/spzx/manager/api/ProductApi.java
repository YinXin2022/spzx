package com.yinxin.spzx.manager.api;

import com.github.pagehelper.PageInfo;
import com.yinxin.spzx.manager.service.SysProductService;
import com.yinxin.spzx.model.entity.product.Product;
import com.yinxin.spzx.model.vo.common.Result;
import com.yinxin.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author YinXin
 * @date 2024-01-30 16:38
 */
@Tag(name = "商品接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys/product")
public class ProductApi {
    private final SysProductService sysProductService;

    @Operation(summary = "获取商品列表")
    @GetMapping("/page/{pageNum}/{pageSize}")
    public Result<PageInfo<Product>> pageBy(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        return Result.build(sysProductService.page(pageNum, pageSize), ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "保存商品")
    @PostMapping("/create")
    public Result createBy(@RequestBody Product product) {
        sysProductService.create(product);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "获取指定id商品")
    @GetMapping("/{id}")
    public Result<Product> getBy(@PathVariable Long id) {
        return Result.build(sysProductService.get(id), ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "修改商品")
    @PutMapping("/update")
    public Result updateBy(@RequestBody Product product) {
        sysProductService.update(product);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "删除商品")
    @DeleteMapping("/delete/{id}")
    public Result deleteBy(@PathVariable Long id) {
        sysProductService.delete(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "审核商品")
    @PutMapping("/{id}/audit/{status}")
    public Result auditBy(@PathVariable Long id, @PathVariable Integer status) {
        sysProductService.audit(id, status);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "上下架商品")
    @PutMapping("/{id}/shelves/{status}")
    public Result shelvesBy(@PathVariable Long id, @PathVariable Integer status) {
        sysProductService.shelves(id, status);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
