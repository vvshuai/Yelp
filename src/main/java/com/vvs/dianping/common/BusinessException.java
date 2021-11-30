package com.vvs.dianping.common;

/**
 * @Author: vvshuai
 * @Description:
 * @Date: Created in 1:35 2021/8/1
 * @Modified By:
 */
public class BusinessException extends Exception{

    private CommonError commonError;

    public BusinessException(EmBusinessError emBusinessError){
        super();
        this.commonError = new CommonError(emBusinessError);
    }

    public BusinessException(EmBusinessError emBusinessError,String errMsg){
        super();
        this.commonError = new CommonError(emBusinessError);
        this.commonError.setErrMsg(errMsg);
    }

    public CommonError getCommonError() {
        return commonError;
    }

    public void setCommonError(CommonError commonError) {
        this.commonError = commonError;
    }
}
