package com.yinxin.spzx.manager.api;

import com.yinxin.spzx.manager.service.SysMenuService;
import com.yinxin.spzx.manager.service.SysUserService;
import com.yinxin.spzx.manager.service.ValidateCodeService;
import com.yinxin.spzx.model.dto.system.LoginDto;
import com.yinxin.spzx.model.entity.system.SysUser;
import com.yinxin.spzx.model.vo.common.Result;
import com.yinxin.spzx.model.vo.common.ResultCodeEnum;
import com.yinxin.spzx.model.vo.system.SysMenuVo;
import com.yinxin.spzx.model.vo.system.ValidateCodeVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-01-12 17:18
 */
@Tag(name = "首页接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys/index")
public class IndexApi {
    private final SysUserService sysUserService;
    private final ValidateCodeService validateCodeService;
    private final SysMenuService sysMenuService;

    @Operation(summary = "登录接口")
    @PostMapping("/login")
    public Result loginBy(@RequestBody LoginDto loginDto) {
        return Result.build(sysUserService.login(loginDto), ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "获取验证码")
    @GetMapping("/validateCode")
    public Result<ValidateCodeVo> getValidateCode() {
        return Result.build(validateCodeService.generateValidateCode(), ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "获取用户信息")
    @GetMapping("/userInfo")
    public Result<SysUser> getUserInfoBy(@RequestHeader String token) {
        return Result.build(sysUserService.getUserInfo(token), ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "退出登录")
    @GetMapping("/logout")
    public Result logout(@RequestHeader String token) {
        sysUserService.logout(token);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "获取用户菜单")
    @GetMapping("/menus")
    public Result<List<SysMenuVo>> menus() {
        return Result.build(sysMenuService.findUserMenuList(), ResultCodeEnum.SUCCESS) ;
    }
}
