package com.vvs.dianping.service.impl;

import com.vvs.dianping.common.BusinessException;
import com.vvs.dianping.common.EmBusinessError;
import com.vvs.dianping.dal.UserModelMapper;
import com.vvs.dianping.model.UserModel;
import com.vvs.dianping.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * @Author: vvshuai
 * @Description:
 * @Date: Created in 23:08 2021/7/11
 * @Modified By:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserModelMapper mUserModelMapper;

    @Override
    public UserModel getUser(Integer id) {
        return mUserModelMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public UserModel register(UserModel registerUser) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        registerUser.setCreatedAt(new Date());
        registerUser.setUpdatedAt(new Date());
        registerUser.setPassword(encodeByMd5(registerUser.getPassword()));
        try {
            mUserModelMapper.insertSelective(registerUser);
        } catch (DuplicateKeyException ex) {
            throw new BusinessException(EmBusinessError.REGISTER_DUP_FAIL);
        }
        return getUser(registerUser.getId());
    }

    @Override
    public UserModel login(String telphone, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException, BusinessException {
        UserModel userModel = mUserModelMapper.selectByTelphoneAndPassword(telphone,encodeByMd5(password));
        if(userModel == null){
            throw new BusinessException(EmBusinessError.LOGIN_FAIL);
        }
        return userModel;
    }

    @Override
    public Integer countAllUser() {
        return mUserModelMapper.countAllUser();
    }

    private String encodeByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // 确认计算方法MD5
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encode(messageDigest.digest(str.getBytes("utf-8")));
    }
}
