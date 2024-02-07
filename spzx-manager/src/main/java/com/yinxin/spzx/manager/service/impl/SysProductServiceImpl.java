package com.yinxin.spzx.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinxin.spzx.manager.mapper.SysProductDetailsMapper;
import com.yinxin.spzx.manager.mapper.SysProductMapper;
import com.yinxin.spzx.manager.mapper.SysProductSkuMapper;
import com.yinxin.spzx.manager.service.SysProductService;
import com.yinxin.spzx.model.entity.product.Product;
import com.yinxin.spzx.model.entity.product.ProductDetails;
import com.yinxin.spzx.model.entity.product.ProductSku;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author YinXin
 * @date 2024-01-30 16:40
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SysProductServiceImpl implements SysProductService {
    private final SysProductMapper sysProductMapper;
    private final SysProductSkuMapper sysProductSkuMapper;
    private final SysProductDetailsMapper sysProductDetailsMapper;

    @Override
    public PageInfo<Product> page(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(sysProductMapper.findAll());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Product product) {
        // 保存商品数据
        product.setStatus(0);              // 设置上架状态为0
        product.setAuditStatus(0);         // 设置审核状态为0
        sysProductMapper.save(product);

        // 保存商品sku数据
        List<ProductSku> productSkuList = product.getProductSkuList();
        for (int i = 0, size = productSkuList.size(); i < size; i++) {
            // 获取ProductSku对象
            ProductSku productSku = productSkuList.get(i);
            productSku.setSkuCode(product.getId() + "_" + i);       // 构建skuCode
            productSku.setProductId(product.getId());               // 设置商品id
            productSku.setSkuName(product.getName() + productSku.getSkuSpec());
            productSku.setSaleNum(0);                               // 设置销量
            productSku.setStatus(0);
            sysProductSkuMapper.save(productSku);                    // 保存数据
        }

        // 保存商品详情数据
        ProductDetails productDetails = new ProductDetails();
        productDetails.setProductId(product.getId());
        productDetails.setImageUrls(product.getDetailsImageUrls());
        sysProductDetailsMapper.save(productDetails);
    }

    @Override
    public Product get(Long id) {
        Product product = sysProductMapper.findById(id);
        // 根据商品的id查询sku数据
        List<ProductSku> productSkuList = sysProductSkuMapper.findByProductId(id);
        product.setProductSkuList(productSkuList);

        // 根据商品的id查询商品详情数据
        ProductDetails productDetails = sysProductDetailsMapper.findByProductId(product.getId());
        product.setDetailsImageUrls(productDetails.getImageUrls());

        // 返回数据
        return product;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Product product) {
        // 修改商品基本数据
        sysProductMapper.updateById(product);

        // 修改商品的sku数据
        List<ProductSku> productSkuList = product.getProductSkuList();
        productSkuList.forEach(productSku -> {
            sysProductSkuMapper.updateById(productSku);
        });

        // 修改商品的详情数据
        ProductDetails productDetails = sysProductDetailsMapper.findByProductId(product.getId());
        productDetails.setImageUrls(product.getDetailsImageUrls());
        sysProductDetailsMapper.updateById(productDetails);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        sysProductMapper.deleteById(id);                   // 根据id删除商品基本数据
        sysProductSkuMapper.deleteByProductId(id);         // 根据商品id删除商品的sku数据
        sysProductDetailsMapper.deleteByProductId(id);     // 根据商品的id删除商品的详情数据
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void audit(Long id, Integer status) {
        Product product = new Product();
        product.setId(id);
        if (status == 1) {
            product.setAuditStatus(1);
            product.setAuditMessage("审批通过");
        } else {
            product.setAuditStatus(-1);
            product.setAuditMessage("审批不通过");
        }
        sysProductMapper.updateById(product);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void shelves(Long id, Integer status) {
        Product product = new Product();
        product.setId(id);
        if (status == 1) {
            product.setStatus(1);
        } else {
            product.setStatus(-1);
        }
        sysProductMapper.updateById(product);
    }
}
