package com.yinxin.user.api;

import com.yinxin.spzx.model.entity.user.UserAddress;
import com.yinxin.spzx.model.vo.common.Result;
import com.yinxin.spzx.model.vo.common.ResultCodeEnum;
import com.yinxin.user.service.UserAddressService;
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
 * @date 2024-03-11 17:40
 */
@Tag(name = "用户接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/userAddress")
public class UserAddressApi {
    private final UserAddressService userAddressService;

    @Operation(summary = "获取用户地址列表")
    @GetMapping("/auth/findUserAddressList")
    public Result<List<UserAddress>> findUserAddressList() {
        return Result.build(userAddressService.findUserAddressList(), ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "获取地址信息")
    @GetMapping("/getUserAddress/{id}")
    public UserAddress getUserAddress(@PathVariable Long id) {
        return userAddressService.getById(id);
    }
}
