package com.vvs.dianping.request;

/**
 * @Author: vvshuai
 * @Description:
 * @Date: Created in 0:04 2021/11/18
 * @Modified By:
 */
public class PageQuery {

    private Integer page = 1;

    private Integer size = 20;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
