package com.yinxin.spzx.manager.api;

import com.yinxin.spzx.manager.service.SysUserService;
import com.yinxin.spzx.manager.service.ValidateCodeService;
import com.yinxin.spzx.model.dto.system.LoginDto;
import com.yinxin.spzx.model.vo.common.Result;
import com.yinxin.spzx.model.vo.common.ResultCodeEnum;
import com.yinxin.spzx.model.vo.system.ValidateCodeVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author YinXin
 * @date 2024-01-12 17:18
 */
@Tag(name = "用户接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys/index")
public class IndexApi {
    private final SysUserService sysUserService;
    private final ValidateCodeService validateCodeService;

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
}
