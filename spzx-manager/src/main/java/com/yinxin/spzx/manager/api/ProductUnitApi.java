package com.yinxin.spzx.manager.api;

import com.yinxin.spzx.manager.service.SysProductUnitService;
import com.yinxin.spzx.model.vo.common.Result;
import com.yinxin.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YinXin
 * @date 2024-01-30 17:39
 */
@Tag(name = "商品单位接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys/productUnit")
public class ProductUnitApi {
    private final SysProductUnitService sysProductUnitService;

    @Operation(summary = "获取所有商品单位")
    @GetMapping("/all")
    public Result all() {
        return Result.build(sysProductUnitService.all(), ResultCodeEnum.SUCCESS);
    }
}
