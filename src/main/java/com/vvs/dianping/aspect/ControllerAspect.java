package com.vvs.dianping.aspect;

import com.vvs.dianping.annotation.AdminPermission;
import com.vvs.dianping.common.CommonError;
import com.vvs.dianping.common.CommonRes;
import com.vvs.dianping.common.EmBusinessError;
import com.vvs.dianping.controller.admin.AdminController;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Author: vvshuai
 * @Description:
 * @Date: Created in 23:31 2021/11/15
 * @Modified By:
 */
@Aspect
@Configuration
public class ControllerAspect {

    private final HttpServletRequest httpServletRequest;

    private final HttpServletResponse httpServletResponse;

    @Autowired(required = false)
    public ControllerAspect(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        this.httpServletRequest = httpServletRequest;
        this.httpServletResponse = httpServletResponse;
    }

    @Around("execution(* com.vvs.dianping.controller.admin.*.*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object adminControllerBeforeValidation(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        AdminPermission adminPermission = method.getAnnotation(AdminPermission.class);
        if (adminPermission == null) {
            // 无需登录

            return joinPoint.proceed();
        }
        String email = (String) httpServletRequest.getSession().getAttribute(AdminController.CURRENT_ADMIN_SESSION);
        if (email == null) {
            if (adminPermission.produceType().equals("text/html")) {
                httpServletResponse.sendRedirect("/admin/admin/loginpage");
                return null;
            } else {
                CommonError commonError = new CommonError(EmBusinessError.ADMIN_SHOULD_LOGIN);
                return CommonRes.create(commonError, "fail");
            }

        } else {
            return joinPoint.proceed();
        }
    }
}
