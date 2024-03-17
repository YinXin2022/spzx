package com.yinxin.user.mapper;

import com.yinxin.spzx.model.entity.base.Region;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-03-12 16:46
 */
@Mapper
public interface RegionMapper {
    List<Region> findByParentCode(String parentCode);
}
