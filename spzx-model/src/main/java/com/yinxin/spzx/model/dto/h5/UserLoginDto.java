package com.yinxin.spzx.model.dto.h5;

import com.alibaba.excel.util.StringUtils;
import com.yinxin.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "用户登录请求参数")
public class UserLoginDto {

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String password;

    public void check() {
        //校验参数
        if (StringUtils.isEmpty(username) ||
                StringUtils.isEmpty(password)) {
            throw new RuntimeException(ResultCodeEnum.DATA_ERROR.getMessage());
        }
    }
}
