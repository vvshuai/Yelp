package com.vvs.dianping.request;

import javax.validation.constraints.NotBlank;

/**
 * @Author: vvshuai
 * @Description:
 * @Date: Created in 23:53 2021/11/17
 * @Modified By:
 */
public class SellerCreateReq {

    @NotBlank(message = "商户名不为空")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SellerCreateReq(@NotBlank(message = "商户名不为空") String name) {
        this.name = name;
    }
}
