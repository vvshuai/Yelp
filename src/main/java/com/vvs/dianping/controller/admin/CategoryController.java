package com.vvs.dianping.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vvs.dianping.annotation.AdminPermission;
import com.vvs.dianping.common.BusinessException;
import com.vvs.dianping.common.CommonUtil;
import com.vvs.dianping.common.EmBusinessError;
import com.vvs.dianping.dal.SellerModelMapper;
import com.vvs.dianping.model.CategoryModel;
import com.vvs.dianping.model.SellerModel;
import com.vvs.dianping.request.CategoryCreateReq;
import com.vvs.dianping.request.PageQuery;
import com.vvs.dianping.request.SellerCreateReq;
import com.vvs.dianping.service.CategoryService;
import com.vvs.dianping.service.SellerService;
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
 * @Date: Created in 22:56 2021/11/21
 * @Modified By:
 */
@Controller("/admin/category")
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // 品类列表
    @RequestMapping("/index")
    @AdminPermission
    public ModelAndView index(PageQuery pageQuery) {

        List<CategoryModel> categoryModels = categoryService.selectAll();

        PageHelper.startPage(pageQuery.getPage(), pageQuery.getSize());
        PageInfo<CategoryModel> categoryModelPageInfo = new PageInfo<>(categoryModels);

        ModelAndView modelAndView = new ModelAndView("/admin/category/index.html");

        modelAndView.addObject("data", categoryModelPageInfo);
        modelAndView.addObject("CONTROLLER_NAME", "category");
        modelAndView.addObject("ACTION_NAME", "index");

        return modelAndView;
    }

    @RequestMapping("/createpage")
    @AdminPermission
    public ModelAndView createPage() {
        ModelAndView modelAndView = new ModelAndView("/admin/category/create.html");

        modelAndView.addObject("CONTROLLER_NAME", "category");
        modelAndView.addObject("ACTION_NAME", "create");

        return modelAndView;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @AdminPermission
    public String create(@Valid CategoryCreateReq categoryCreateReq, BindingResult bindingResult) throws BusinessException {
        if (bindingResult.hasErrors()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }

        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setName(categoryCreateReq.getName());
        categoryModel.setIconUrl(categoryCreateReq.getIconUrl());
        categoryModel.setSort(categoryCreateReq.getSort());
        categoryService.create(categoryModel);

        return "redirect:/admin/category/index";
    }
}
