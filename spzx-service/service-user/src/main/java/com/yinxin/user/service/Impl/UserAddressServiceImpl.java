package com.yinxin.user.service.Impl;

import com.yinxin.common.utils.AuthContextUtil;
import com.yinxin.spzx.model.entity.user.UserAddress;
import com.yinxin.user.mapper.UserAddressMapper;
import com.yinxin.user.service.UserAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-03-11 17:41
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserAddressServiceImpl implements UserAddressService {
    private final UserAddressMapper userAddressMapper;

    @Override
    public List<UserAddress> findUserAddressList() {
        Long userId = AuthContextUtil.getUserInfo().getId();
        return userAddressMapper.findByUserId(userId);
    }

    @Override
    public UserAddress getById(Long id) {
        return userAddressMapper.findById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(UserAddress userAddress) {
        Long userId = AuthContextUtil.getUserInfo().getId();
        userAddress.setUserId(userId);
        userAddressMapper.save(userAddress);
    }
}
