package com.vvs.dianping.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vvs.dianping.annotation.AdminPermission;
import com.vvs.dianping.common.BusinessException;
import com.vvs.dianping.common.CommonUtil;
import com.vvs.dianping.common.EmBusinessError;
import com.vvs.dianping.model.CategoryModel;
import com.vvs.dianping.model.ShopModel;
import com.vvs.dianping.request.CategoryCreateReq;
import com.vvs.dianping.request.PageQuery;
import com.vvs.dianping.request.ShopCreateReq;
import com.vvs.dianping.service.CategoryService;
import com.vvs.dianping.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author: vvshuai
 * @Description:
 * @Date: Created in 0:44 2021/11/28
 * @Modified By:
 */
@Controller("/admin/shop")
@RequestMapping("/admin/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    // 门店列表
    @RequestMapping("/index")
    @AdminPermission
    public ModelAndView index(PageQuery pageQuery) {

        List<ShopModel> shopModelList = shopService.selectAll();

        PageHelper.startPage(pageQuery.getPage(), pageQuery.getSize());
        PageInfo<ShopModel> shopModelPageInfo = new PageInfo<>(shopModelList);

        ModelAndView modelAndView = new ModelAndView("/admin/shop/index.html");

        modelAndView.addObject("data", shopModelPageInfo);
        modelAndView.addObject("CONTROLLER_NAME", "shop");
        modelAndView.addObject("ACTION_NAME", "index");

        return modelAndView;
    }

    @RequestMapping("/createpage")
    @AdminPermission
    public ModelAndView createPage() {
        ModelAndView modelAndView = new ModelAndView("/admin/shop/create.html");

        modelAndView.addObject("CONTROLLER_NAME", "shop");
        modelAndView.addObject("ACTION_NAME", "create");

        return modelAndView;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @AdminPermission
    public String create(@Valid ShopCreateReq shopCreateReq, BindingResult bindingResult) throws BusinessException {
        if (bindingResult.hasErrors()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }
        ShopModel shopModel = new ShopModel();
        shopModel.setIconUrl(shopCreateReq.getIconUrl());
        shopModel.setAddress(shopCreateReq.getAddress());
        shopModel.setCategortId(shopCreateReq.getCategoryId());
        shopModel.setEndTime(shopCreateReq.getEndTime());
        shopModel.setStartTime(shopCreateReq.getStartTime());
        shopModel.setStartTime(shopCreateReq.getStartTime());
        shopModel.setLongitude(shopCreateReq.getLongitude());
        shopModel.setLatitude(shopCreateReq.getLatitude());
        shopModel.setName(shopCreateReq.getName());
        shopModel.setPricePerMan(shopCreateReq.getPricePerMan());
        shopModel.setSellerId(shopCreateReq.getSellerId());

        shopService.create(shopModel);

        return "redirect:/admin/shop/index";
    }
}