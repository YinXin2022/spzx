package com.yinxin.spzx.product.service;

import com.github.pagehelper.PageInfo;
import com.yinxin.spzx.model.dto.h5.ProductSkuDto;
import com.yinxin.spzx.model.entity.product.ProductSku;
import com.yinxin.spzx.model.vo.h5.ProductItemVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-02-28 15:28
 */
@Service
public interface ProductService {
    List<ProductSku> findProductSkuBySale();

    PageInfo<ProductSku> findByPage(Integer page, Integer limit, ProductSkuDto productSkuDto);

    ProductItemVo item(Long skuId);
}
