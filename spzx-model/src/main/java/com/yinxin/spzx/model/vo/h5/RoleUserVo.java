package com.yinxin.spzx.model.vo.h5;

import com.yinxin.spzx.model.entity.system.SysRole;
import com.yinxin.spzx.model.entity.system.SysUser;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author YinXin
 * @date 2024-01-17 15:50
 */
@Data
@Schema(description = "角色用户关联对象")
public class RoleUserVo {
    @Schema(description = "角色")
    private SysRole sysRole;

    @Schema(description = "用户")
    private SysUser sysUser;
}
