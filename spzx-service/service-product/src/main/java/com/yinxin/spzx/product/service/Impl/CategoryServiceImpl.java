package com.yinxin.spzx.product.service.Impl;

import com.github.xiaoymin.knife4j.core.util.CollectionUtils;
import com.yinxin.common.contest.RedisContest;
import com.yinxin.common.redis.RedisCache;
import com.yinxin.spzx.model.entity.product.Category;
import com.yinxin.spzx.product.mapper.CategoryMapper;
import com.yinxin.spzx.product.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author YinXin
 * @date 2024-02-28 15:27
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final RedisCache redisCache;
    private final CategoryMapper categoryMapper;

    @Override
    public List<Category> findOneCategory() {
        return categoryMapper.findOneCategory();
    }

    @Override
    public List<Category> findCategoryTree() {
        Object cacheObject = redisCache.getCacheObject(RedisContest.CATEGORY_TREE);
        if (cacheObject != null) {
            return (List<Category>) redisCache.getCacheObject(RedisContest.CATEGORY_TREE);
        }

        List<Category> categoryList = categoryMapper.findAll();
        List<Category> oneCategoryList = categoryList.stream().filter(item -> item.getParentId().longValue() == 0).collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(oneCategoryList)) {
            oneCategoryList.forEach(oneCategory -> {
                List<Category> twoCategoryList = categoryList.stream().filter(item -> item.getParentId().longValue() == oneCategory.getId().longValue()).collect(Collectors.toList());
                oneCategory.setChildren(twoCategoryList);

                if (!CollectionUtils.isEmpty(twoCategoryList)) {
                    twoCategoryList.forEach(twoCategory -> {
                        List<Category> threeCategoryList = categoryList.stream().filter(item -> item.getParentId().longValue() == twoCategory.getId().longValue()).collect(Collectors.toList());
                        twoCategory.setChildren(threeCategoryList);
                    });
                }
            });
        }
        redisCache.setCacheObject(RedisContest.CATEGORY_TREE, oneCategoryList, 5, TimeUnit.MINUTES);
        return oneCategoryList;
    }
}
