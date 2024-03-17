package com.yinxin.user.service;

import com.yinxin.spzx.model.entity.base.Region;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-03-12 16:44
 */
@Service
public interface RegionService {
    List<Region> findByParentCode(String parentCode);
}
