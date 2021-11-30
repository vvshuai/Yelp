package com.vvs.dianping.common;

/**
 * @Author: vvshuai
 * @Description:
 * @Date: Created in 1:24 2021/8/1
 * @Modified By:
 */
public class CommonRes {

    /**
     * 处理结果
     */
    private String status;

    /**
     * 使用通用错误码
     */
    private Object data;

    // 通用创建返回对象方法
    public static CommonRes create(Object result) {
        return CommonRes.create(result, "success");
    }

    public static CommonRes create(Object result, String status) {
        CommonRes commonRes = new CommonRes();
        commonRes.setData(result);
        commonRes.setStatus(status);

        return commonRes;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
