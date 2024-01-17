package com.yinxin.spzx.manager.api;

import com.yinxin.spzx.manager.service.SysMenuService;
import com.yinxin.spzx.model.entity.system.SysMenu;
import com.yinxin.spzx.model.vo.common.Result;
import com.yinxin.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-01-17 16:54
 */
@Tag(name = "菜单接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys/menu")
public class SysMenuApi {
    private final SysMenuService sysMenuService;

    @Operation(summary = "查询所有菜单")
    @GetMapping("/list")
    public Result<List<SysMenu>> listBy() {
        return Result.build(sysMenuService.list(), ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "创建菜单")
    @PostMapping("/create")
    public Result createBy(@RequestBody SysMenu sysMenu) {
        sysMenuService.create(sysMenu);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "修改菜单")
    @PutMapping("/update")
    public Result updateBy(@RequestBody SysMenu sysMenu) {
        sysMenuService.update(sysMenu);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "删除菜单")
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Long id) {
        sysMenuService.delete(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
