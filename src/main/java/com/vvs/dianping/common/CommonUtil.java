package com.vvs.dianping.common;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * @Author: vvshuai
 * @Description:
 * @Date: Created in 15:20 2021/8/14
 * @Modified By:
 */
public class CommonUtil {

    public static String processErrorString(BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getDefaultMessage() + ",");
        }

        return sb.substring(0, sb.length()-1);
    }
}
