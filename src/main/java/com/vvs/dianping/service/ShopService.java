package com.vvs.dianping.service;

import com.vvs.dianping.common.BusinessException;
import com.vvs.dianping.model.ShopModel;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: vvshuai
 * @Description:
 * @Date: Created in 0:29 2021/11/28
 * @Modified By:
 */
public interface ShopService {

    ShopModel create(ShopModel shopModel) throws BusinessException;

    ShopModel get(Integer id);

    List<ShopModel> selectAll();

    Integer countAllShop();

    List<ShopModel> recommend(BigDecimal longitude, BigDecimal latitude);

    List<ShopModel> search(BigDecimal longitude, BigDecimal latitude, String keyword);
}
