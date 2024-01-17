package com.yinxin.spzx.manager.api;

import com.github.pagehelper.PageInfo;
import com.yinxin.spzx.manager.service.SysUserService;
import com.yinxin.spzx.model.dto.system.AssginRoleDto;
import com.yinxin.spzx.model.dto.system.SysUserDto;
import com.yinxin.spzx.model.entity.system.SysUser;
import com.yinxin.spzx.model.vo.common.Result;
import com.yinxin.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author YinXin
 * @date 2024-01-14 11:06
 */
@Tag(name = "用户接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys/user")
public class SysUserApi {
    private final SysUserService sysUserService;

    @PostMapping("/page/{pageNum}/{pageSize}")
    public Result<PageInfo<SysUser>> pageBy(@RequestBody SysUserDto sysUserDto, @PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        return Result.build(sysUserService.pageBy(sysUserDto, pageNum, pageSize), ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/create")
    public Result createBy(@RequestBody SysUser sysUser) {
        sysUserService.create(sysUser);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @PutMapping("/update/{predicate}")
    public Result updateBy(@RequestBody SysUser sysUser, @PathVariable boolean predicate) {
        sysUserService.update(sysUser, predicate);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteBy(@PathVariable Long id) {
        sysUserService.delete(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/addRoles")
    public Result addRolesBy(@RequestBody AssginRoleDto assginRoleDto) {
        sysUserService.addRoles(assginRoleDto);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
