package com.yinxin.spzx.product.api;

import com.yinxin.spzx.model.entity.product.Brand;
import com.yinxin.spzx.model.vo.common.Result;
import com.yinxin.spzx.model.vo.common.ResultCodeEnum;
import com.yinxin.spzx.product.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-02-29 16:56
 */
@Tag(name = "品牌接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product/brand")
public class BrandApi {
    private final BrandService brandService;

    @Operation(summary = "获取所有品牌")
    @GetMapping("/findAll")
    public Result<List<Brand>> findAll() {
        return Result.build(brandService.findAll(), ResultCodeEnum.SUCCESS);
    }

}
