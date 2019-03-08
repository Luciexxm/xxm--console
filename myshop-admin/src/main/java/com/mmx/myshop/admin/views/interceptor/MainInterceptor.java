package com.mmx.myshop.admin.views.interceptor;

import com.mmx.myshop.admin.common.contants.Contants;
import com.mmx.myshop.domain.TbUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: myshop
 * @description: 主页拦截
 * @author: mmx
 * @create: 2018-08-31 22:06
 */
public class MainInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        TbUser admin = (TbUser) httpServletRequest.getSession().getAttribute(Contants.Session_User);
        if( modelAndView != null && modelAndView.getViewName().endsWith("login")){
            //用户没有登录 返回登录
            if(admin == null ){
                return;
            }
            else{
                // 用户登录 直接去首页
                httpServletResponse.sendRedirect("/main");
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
