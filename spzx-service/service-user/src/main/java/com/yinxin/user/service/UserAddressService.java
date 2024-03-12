package com.yinxin.user.service;

import com.yinxin.spzx.model.entity.user.UserAddress;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-03-11 17:40
 */
@Service
public interface UserAddressService {
    List<UserAddress> findUserAddressList();

    UserAddress getById(Long id);
}
