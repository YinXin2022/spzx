package com.yinxin.user.api;

import com.yinxin.spzx.model.entity.base.Region;
import com.yinxin.spzx.model.vo.common.Result;
import com.yinxin.spzx.model.vo.common.ResultCodeEnum;
import com.yinxin.user.service.RegionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-03-12 16:42
 */
@Tag(name = "地区接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/region")
public class RegionApi {
    private final RegionService regionService;

    @GetMapping("/findByParentCode/{parentCode}")
    @Operation(summary = "根据父级编码查询地区列表")
    public Result<List<Region>> findByParentCode(@PathVariable String parentCode) {
        return Result.build(regionService.findByParentCode(parentCode), ResultCodeEnum.SUCCESS);
    }
}
