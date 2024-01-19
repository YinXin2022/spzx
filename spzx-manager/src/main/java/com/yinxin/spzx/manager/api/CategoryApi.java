package com.yinxin.spzx.manager.api;

import com.yinxin.spzx.manager.service.SysCategoryService;
import com.yinxin.spzx.model.entity.product.Category;
import com.yinxin.spzx.model.vo.common.Result;
import com.yinxin.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-01-19 10:55
 */
@Tag(name = "分类接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys/category")
public class CategoryApi {
    private final SysCategoryService sysCategoryService;

    @Operation(summary = "获取某一层的分类")
    @GetMapping("/{parentId}")
    public Result<List<Category>> queryBy(@PathVariable Long parentId) {
        return Result.build(sysCategoryService.queryByParentId(parentId), ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "导出数据")
    @GetMapping("/export")
    public void export(HttpServletResponse response) {
        sysCategoryService.export(response);
    }

    @Operation(summary = "导入数据")
    @PostMapping("/import")
    public Result importData(MultipartFile file) {
        sysCategoryService.importData(file);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
