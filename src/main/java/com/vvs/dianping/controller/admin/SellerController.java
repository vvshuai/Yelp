package com.vvs.dianping.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vvs.dianping.annotation.AdminPermission;
import com.vvs.dianping.common.BusinessException;
import com.vvs.dianping.common.CommonRes;
import com.vvs.dianping.common.CommonUtil;
import com.vvs.dianping.common.EmBusinessError;
import com.vvs.dianping.dal.SellerModelMapper;
import com.vvs.dianping.model.SellerModel;
import com.vvs.dianping.request.PageQuery;
import com.vvs.dianping.request.SellerCreateReq;
import com.vvs.dianping.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author: vvshuai
 * @Description:
 * @Date: Created in 23:33 2021/11/17
 * @Modified By:
 */
@Controller("/admin/seller")
@RequestMapping("/admin/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    // 商户列表
    @RequestMapping("/index")
    @AdminPermission
    public ModelAndView index(PageQuery pageQuery) {

        List<SellerModel> sellerModels = sellerService.selectAll();

        PageHelper.startPage(pageQuery.getPage(), pageQuery.getSize());
        PageInfo<SellerModel> sellerModelPageInfo = new PageInfo<>(sellerModels);

        ModelAndView modelAndView = new ModelAndView("/admin/seller/index.html");

        modelAndView.addObject("data", sellerModelPageInfo);
        modelAndView.addObject("CONTROLLER_NAME", "seller");
        modelAndView.addObject("ACTION_NAME", "index");

        return modelAndView;
    }

    @RequestMapping("/createpage")
    @AdminPermission
    public ModelAndView createPage() {
        ModelAndView modelAndView = new ModelAndView("/admin/seller/create.html");

        modelAndView.addObject("CONTROLLER_NAME", "seller");
        modelAndView.addObject("ACTION_NAME", "create");

        return modelAndView;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @AdminPermission
    public String create(@Valid SellerCreateReq sellerCreateReq, BindingResult bindingResult) throws BusinessException {
        if (bindingResult.hasErrors()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }

        SellerModel sellerModel = new SellerModel();
        sellerModel.setName(sellerCreateReq.getName());

        sellerService.create(sellerModel);

        return "redirect:/admin/seller/index";
    }

    @RequestMapping(value = "down", method = RequestMethod.POST)
    @AdminPermission
    @ResponseBody
    public CommonRes down(@RequestParam(value = "id") Integer id) throws BusinessException {
        SellerModel sellerModel = sellerService.changeStatus(id, 1);
        return CommonRes.create(sellerModel);
    }

    @RequestMapping(value = "up", method = RequestMethod.POST)
    @AdminPermission
    @ResponseBody
    public CommonRes up(@RequestParam(value = "id") Integer id) throws BusinessException {
        SellerModel sellerModel = sellerService.changeStatus(id, 0);
        return CommonRes.create(sellerModel);
    }
}
