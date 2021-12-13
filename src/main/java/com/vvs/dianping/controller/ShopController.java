package com.vvs.dianping.controller;

import com.vvs.dianping.common.BusinessException;
import com.vvs.dianping.common.CommonRes;
import com.vvs.dianping.common.EmBusinessError;
import com.vvs.dianping.model.ShopModel;
import com.vvs.dianping.service.CategoryService;
import com.vvs.dianping.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: vvshuai
 * @Description:
 * @Date: Created in 0:18 2021/12/4
 * @Modified By:
 */
@Controller("/shop")
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private CategoryService categoryService;

    // 推荐服务 V1.0
    @RequestMapping("/recommend")
    @ResponseBody
    public CommonRes recommend(@RequestParam(name="longitude") BigDecimal longitude,
                               @RequestParam(name="latitude") BigDecimal latitude) throws BusinessException {
        if (longitude == null || latitude == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        List<ShopModel> shopModelList = shopService.recommend(longitude, latitude);

        return CommonRes.create(shopModelList);
    }

    // 搜索服务V1.0
    @RequestMapping("/search")
    @ResponseBody
    public CommonRes search(@RequestParam(name="longitude") BigDecimal longitude,
                            @RequestParam(name="latitude") BigDecimal latitude,
                            @RequestParam(name="keyword") String keyword) throws BusinessException {
        if (StringUtils.isEmpty(keyword) || longitude == null || latitude == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        List<ShopModel> shopModelList = shopService.search(longitude, latitude, keyword);
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("shop", shopModelList);
        return CommonRes.create(resMap);
    }
}
