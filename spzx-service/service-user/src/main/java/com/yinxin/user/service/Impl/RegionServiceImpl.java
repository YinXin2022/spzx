package com.yinxin.user.service.Impl;

import com.yinxin.spzx.model.entity.base.Region;
import com.yinxin.user.mapper.RegionMapper;
import com.yinxin.user.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-03-12 16:44
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {
    private final RegionMapper regionMapper;

    @Override
    public List<Region> findByParentCode(String parentCode) {
        return regionMapper.findByParentCode(parentCode);
    }
}
