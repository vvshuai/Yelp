package com.vvs.dianping.service;

import com.vvs.dianping.common.BusinessException;
import com.vvs.dianping.model.CategoryModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: vvshuai
 * @Description:
 * @Date: Created in 0:56 2021/11/21
 * @Modified By:
 */
public interface CategoryService {

    CategoryModel create(CategoryModel categoryModel) throws BusinessException;

    CategoryModel get(Integer id);

    List<CategoryModel> selectAll();
}
