package com.vvs.dianping.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vvs.dianping.annotation.AdminPermission;
import com.vvs.dianping.common.BusinessException;
import com.vvs.dianping.common.CommonRes;
import com.vvs.dianping.common.CommonUtil;
import com.vvs.dianping.common.EmBusinessError;
import com.vvs.dianping.model.CategoryModel;
import com.vvs.dianping.request.CategoryCreateReq;
import com.vvs.dianping.request.PageQuery;
import com.vvs.dianping.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author: vvshuai
 * @Description:
 * @Date: Created in 22:56 2021/11/21
 * @Modified By:
 */
@Controller("/category")
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ResponseBody
    @RequestMapping("/list")
    public CommonRes list() {
        List<CategoryModel> categoryModels = categoryService.selectAll();

        return CommonRes.create(categoryModels);
    }
}
