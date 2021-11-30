package com.vvs.dianping.service;

import com.vvs.dianping.common.BusinessException;
import com.vvs.dianping.model.SellerModel;

import java.util.List;

/**
 * @Author: vvshuai
 * @Description:
 * @Date: Created in 23:31 2021/11/17
 * @Modified By:
 */
public interface SellerService {

    SellerModel create(SellerModel sellerModel);

    SellerModel get(Integer id);

    List<SellerModel> selectAll();

    SellerModel changeStatus(Integer id, Integer disabledFlag) throws BusinessException;
}
