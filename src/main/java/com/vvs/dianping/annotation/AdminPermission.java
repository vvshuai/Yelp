package com.vvs.dianping.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: vvshuai
 * @Description:
 * @Date: Created in 23:30 2021/11/15
 * @Modified By:
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AdminPermission {
    String produceType() default "text/html";
}
