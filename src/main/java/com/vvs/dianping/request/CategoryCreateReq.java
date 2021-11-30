package com.vvs.dianping.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author: vvshuai
 * @Description:
 * @Date: Created in 23:01 2021/11/21
 * @Modified By:
 */
public class CategoryCreateReq {

    @NotBlank(message = "名字不能为空")
    private String name;

    @NotBlank(message = "iconUrl不能为空")
    private String iconUrl;

    @NotNull(message = "名字不能为空")
    private Integer sort;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
