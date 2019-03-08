package com.mmx.myshop.admin.views.interceptor;

import com.mmx.myshop.admin.common.contants.Contants;
import com.mmx.myshop.domain.TbUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: myshop
 * @description: 登录拦截
 * @author: mmx
 * @create: 2018-08-31 22:05
 */
public class LoginInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        TbUser admin = (TbUser) httpServletRequest.getSession().getAttribute(Contants.Session_User);
        if(admin == null){
            httpServletResponse.sendRedirect("login");
           return false;
       }
       return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
