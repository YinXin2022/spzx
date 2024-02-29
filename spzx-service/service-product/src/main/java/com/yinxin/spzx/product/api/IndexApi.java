package com.yinxin.spzx.product.api;

import com.yinxin.spzx.model.entity.product.Category;
import com.yinxin.spzx.model.entity.product.ProductSku;
import com.yinxin.spzx.model.vo.common.Result;
import com.yinxin.spzx.model.vo.common.ResultCodeEnum;
import com.yinxin.spzx.model.vo.h5.IndexVo;
import com.yinxin.spzx.product.service.CategoryService;
import com.yinxin.spzx.product.service.ProductService;
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
 * @date 2024-02-28 15:26
 */

@Tag(name = "首页接口")
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product/index")
public class IndexApi {
    private final CategoryService categoryService;
    private final ProductService productService;

    @Operation(summary = "获取首页数据")
    @GetMapping
    public Result<IndexVo> findData() {
        List<Category> categoryList = categoryService.findOneCategory();
        List<ProductSku> productSkuList = productService.findProductSkuBySale();
        IndexVo indexVo = new IndexVo();
        indexVo.setCategoryList(categoryList);
        indexVo.setProductSkuList(productSkuList);
        return Result.build(indexVo, ResultCodeEnum.SUCCESS);
    }
}
