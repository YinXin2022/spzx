package com.yinxin.user.mapper;

import com.yinxin.spzx.model.entity.user.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author YinXin
 * @date 2024-03-10 14:12
 */
@Mapper
public interface UserInfoMapper {
    UserInfo getByUsername(String username);

    void save(UserInfo user);
}
