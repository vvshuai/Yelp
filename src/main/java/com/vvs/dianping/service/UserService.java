package com.vvs.dianping.service;

import com.vvs.dianping.common.BusinessException;
import com.vvs.dianping.model.UserModel;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: vvshuai
 * @Description:
 * @Date: Created in 23:08 2021/7/11
 * @Modified By:
 */
public interface UserService {

    UserModel getUser(final Integer id);

    UserModel register(UserModel registerUser) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException;

    UserModel login(String telphone, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException, BusinessException;

    Integer countAllUser();
}
