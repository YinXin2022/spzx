package com.yinxin.spzx.manager.api;

import com.github.pagehelper.PageInfo;
import com.yinxin.spzx.manager.service.SysRoleService;
import com.yinxin.spzx.model.dto.system.SysRoleDto;
import com.yinxin.spzx.model.entity.system.SysRole;
import com.yinxin.spzx.model.vo.common.Result;
import com.yinxin.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author YinXin
 * @date 2024-01-13 14:56
 */
@Tag(name = "角色接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys/role")
public class SysRoleApi {
    private final SysRoleService sysRoleService;

    @Operation(summary = "分页查询角色")
    @PostMapping("/page/{pageNum}/{pageSize}")
    public Result<PageInfo<SysRole>> pageBy(@RequestBody SysRoleDto queryDto,
                                           @PathVariable(value = "pageNum") Integer pageNum,
                                           @PathVariable(value = "pageSize") Integer pageSize) {
        return Result.build(sysRoleService.page(queryDto, pageNum, pageSize), ResultCodeEnum.SUCCESS);
    }
}
