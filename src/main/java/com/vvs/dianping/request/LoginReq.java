package com.vvs.dianping.request;

import javax.validation.constraints.NotBlank;

/**
 * @Author: vvshuai
 * @Description:
 * @Date: Created in 15:54 2021/8/14
 * @Modified By:
 */
public class LoginReq {

    @NotBlank(message = "手机号不为空")
    private String telphone;

    @NotBlank(message = "密码不能为空")
    private String password;

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
