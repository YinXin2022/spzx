package com.yinxin.user.api;

import com.yinxin.spzx.model.dto.h5.UserLoginDto;
import com.yinxin.spzx.model.dto.h5.UserRegisterDto;
import com.yinxin.spzx.model.vo.common.Result;
import com.yinxin.spzx.model.vo.common.ResultCodeEnum;
import com.yinxin.spzx.model.vo.h5.UserInfoVo;
import com.yinxin.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author YinXin
 * @date 2024-03-10 13:53
 */
@Tag(name = "用户接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/userInfo")
public class UserApi {
    private final UserService userService;

    @Operation(summary = "会员注册")
    @PostMapping("/register")
    public Result register(@RequestBody UserRegisterDto userRegisterDto) {
        userService.register(userRegisterDto);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    @Operation(summary = "会员登录")
    @PostMapping("/login")
    public Result login(@RequestBody UserLoginDto userLoginDto) {
        return Result.build(userService.login(userLoginDto), ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "获取当前登录用户信息")
    @GetMapping("/auth/getCurrentUserInfo")
    public Result<UserInfoVo> getCurrentUserInfo(@RequestHeader String token) {
        UserInfoVo userInfoVo = userService.getCurrentUserInfo(token) ;
        return Result.build(userInfoVo , ResultCodeEnum.SUCCESS) ;
    }
}
