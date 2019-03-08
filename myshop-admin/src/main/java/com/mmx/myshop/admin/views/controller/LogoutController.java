package com.mmx.myshop.admin.views.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 注销
 */
@Controller
public class LogoutController {
    @RequestMapping(value = {"logout"},method = RequestMethod.GET)
    public String lougout( HttpServletRequest request, HttpServletResponse httpServletResponse){
        request.getSession().invalidate();
        //如果UserinfoCookies  为空  Remember 为空
        // 否则 Remember 为on
        isRememberMe(request);
        return "redirect:/login";
    }






    private void isRememberMe(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            if("UserInfoCookie".equals(cookies[i].getName())){
                if(cookies[i].getValue() == ""){
                    request.getSession().setAttribute("remember","");
                }
                else{
                    request.getSession().setAttribute("remember","on");
                }

            }

        }
    }

}
