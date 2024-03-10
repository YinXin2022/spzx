package com.yinxin.spzx.product.api;

import com.github.pagehelper.PageInfo;
import com.yinxin.spzx.model.dto.h5.ProductSkuDto;
import com.yinxin.spzx.model.entity.product.ProductSku;
import com.yinxin.spzx.model.vo.common.Result;
import com.yinxin.spzx.model.vo.common.ResultCodeEnum;
import com.yinxin.spzx.model.vo.h5.ProductItemVo;
import com.yinxin.spzx.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YinXin
 * @date 2024-02-29 17:18
 */
@Tag(name = "商品接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductApi {
    private final ProductService productService;

    @Operation(summary = "分页查询")
    @GetMapping(value = "/{page}/{limit}")
    public Result<PageInfo<ProductSku>> findByPage(@Parameter(name = "page", description = "当前页码", required = true) @PathVariable Integer page,
                                                   @Parameter(name = "limit", description = "每页记录数", required = true) @PathVariable Integer limit,
                                                   @Parameter(name = "productSkuDto", description = "搜索条件对象", required = false) ProductSkuDto productSkuDto) {
        PageInfo<ProductSku> pageInfo = productService.findByPage(page, limit, productSkuDto);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "商品详情")
    @GetMapping("/item/{skuId}")
    public Result<ProductItemVo> item(@Parameter(name = "skuId", description = "商品skuId", required = true) @PathVariable Long skuId) {
        ProductItemVo productItemVo = productService.item(skuId);
        return Result.build(productItemVo , ResultCodeEnum.SUCCESS);
    }
}
