package com.vvs.dianping.controller;

import com.vvs.dianping.common.BusinessException;
import com.vvs.dianping.common.CommonRes;
import com.vvs.dianping.common.CommonUtil;
import com.vvs.dianping.common.EmBusinessError;
import com.vvs.dianping.model.UserModel;
import com.vvs.dianping.request.LoginReq;
import com.vvs.dianping.request.RegisterReq;
import com.vvs.dianping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: vvshuai
 * @Description:
 * @Date: Created in 2:11 2021/7/11
 * @Modified By:
 */
@Controller("/user")
@RequestMapping("/user")
public class UserController {

    public static final String CURRENT_USER_SESSION = "currentUserSession";

    @Autowired
    private UserService mUserService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @GetMapping("/test")
    @ResponseBody
    public String test() {

        return "test";
    }

    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("/index.html");
        return modelAndView;
    }

    @RequestMapping("/get")
    @ResponseBody
    public CommonRes getUser(@RequestParam(name="id") Integer id) throws BusinessException {
        UserModel userModel = mUserService.getUser(id);
        if (userModel == null) {
            throw new BusinessException(EmBusinessError.NO_OBJECT_FOUND);
        }
        return CommonRes.create(mUserService.getUser(id));
    }

    @RequestMapping("/register")
    @ResponseBody
    public CommonRes register(@Valid @RequestBody RegisterReq registerReq, BindingResult bindingResult) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if (bindingResult.hasErrors()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        UserModel registerUser = new UserModel();
        registerUser.setTelphone(registerReq.getTelphone());
        registerUser.setPassword(registerReq.getPassword());
        registerUser.setNickName(registerReq.getNickName());
        registerUser.setGender(registerReq.getGender());

        UserModel userModel = mUserService.register(registerUser);

        return CommonRes.create(userModel);
    }

    @RequestMapping("/login")
    @ResponseBody
    public CommonRes login(@RequestBody @Valid LoginReq loginReq, BindingResult bindingResult) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if (bindingResult.hasErrors()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }
        UserModel userModel = mUserService.login(loginReq.getTelphone(), loginReq.getPassword());
        httpServletRequest.getSession().setAttribute(CURRENT_USER_SESSION, userModel);

        return CommonRes.create(userModel);
    }

    @RequestMapping("/getcurrentuser")
    @ResponseBody
    public CommonRes getCurrentUser() {
        UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute(CURRENT_USER_SESSION);

        return CommonRes.create(userModel);
    }
}
