package com.vvs.dianping.service.impl;

import com.vvs.dianping.common.BusinessException;
import com.vvs.dianping.common.EmBusinessError;
import com.vvs.dianping.dal.ShopModelMapper;
import com.vvs.dianping.model.CategoryModel;
import com.vvs.dianping.model.SellerModel;
import com.vvs.dianping.model.ShopModel;
import com.vvs.dianping.service.CategoryService;
import com.vvs.dianping.service.SellerService;
import com.vvs.dianping.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author: vvshuai
 * @Description:
 * @Date: Created in 0:30 2021/11/28
 * @Modified By:
 */
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopModelMapper shopModelMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SellerService sellerService;

    @Override
    @Transactional
    public ShopModel create(ShopModel shopModel) throws BusinessException {
        shopModel.setCreatedAt(new Date());
        shopModel.setUpdatedAt(new Date());

        // 校验商家是否存在正确
        SellerModel sellerModel = sellerService.get(shopModel.getSellerId());
        if (sellerModel == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "商户不存在");
        }
        if (sellerModel.getDisabledFlag() == 1) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "商户已经禁用");
        }

        // 校验类目
        CategoryModel categoryModel = categoryService.get(shopModel.getCategortId());

        if (categoryModel == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "类目不存在");
        }

        shopModelMapper.insertSelective(shopModel);

        return get(shopModel.getId());
    }

    @Override
    public ShopModel get(Integer id) {
        ShopModel shopModel = shopModelMapper.selectByPrimaryKey(id);

        if (shopModel == null) {
            return null;
        }
        shopModel.setSellerModel(sellerService.get(shopModel.getSellerId()));
        shopModel.setCategoryModel(categoryService.get(shopModel.getCategortId()));

        return shopModel;
    }

    @Override
    public List<ShopModel> selectAll() {
        List<ShopModel> shopModelList = shopModelMapper.selectAll();
        shopModelList.forEach(shopModel -> {
            shopModel.setSellerModel(sellerService.get(shopModel.getSellerId()));
            shopModel.setCategoryModel(categoryService.get(shopModel.getCategortId()));

        });
        return shopModelList;
    }
}
