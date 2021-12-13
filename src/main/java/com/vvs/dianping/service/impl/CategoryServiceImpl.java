package com.vvs.dianping.service.impl;

import com.vvs.dianping.common.BusinessException;
import com.vvs.dianping.common.EmBusinessError;
import com.vvs.dianping.dal.CategoryModelMapper;
import com.vvs.dianping.model.CategoryModel;
import com.vvs.dianping.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author: vvshuai
 * @Description:
 * @Date: Created in 22:06 2021/11/21
 * @Modified By:
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryModelMapper categoryModelMapper;

    @Override
    @Transactional
    public CategoryModel create(CategoryModel categoryModel) throws BusinessException {
        categoryModel.setCreatedAt(new Date());
        categoryModel.setUpdatedAt(new Date());
        try {
            categoryModelMapper.insertSelective(categoryModel);
        } catch (DuplicateKeyException e) {
            throw new BusinessException(EmBusinessError.CATEGORY_NAME_DUPLICATED);
        }
        return get(categoryModel.getId());
    }

    @Override
    public CategoryModel get(Integer id) {
        return categoryModelMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CategoryModel> selectAll() {
        return categoryModelMapper.selectAll();
    }

    @Override
    public Integer countAllCategory() {
        return categoryModelMapper.countAllCategory();
    }
}
