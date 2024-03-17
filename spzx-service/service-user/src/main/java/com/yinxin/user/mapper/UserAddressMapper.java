package com.yinxin.user.mapper;

import com.yinxin.spzx.model.entity.user.UserAddress;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-03-11 17:42
 */
@Mapper
public interface UserAddressMapper {

    List<UserAddress> findByUserId(Long userId);

    UserAddress findById(Long id);

    void save(UserAddress userAddress);
}
