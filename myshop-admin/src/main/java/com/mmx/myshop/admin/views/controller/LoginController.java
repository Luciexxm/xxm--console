package com.mmx.myshop.admin.views.controller;

import com.mmx.myshop.admin.common.contants.Contants;
import com.mmx.myshop.common.utils.CookiesUtil;
import com.mmx.myshop.admin.service.TbUserService;
import com.mmx.myshop.domain.TbUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    @Autowired
    private TbUserService tbUserService;

    @RequestMapping(value = {"", "login"}, method = RequestMethod.GET)
    public String getlogin(HttpServletRequest request,HttpServletRequest httpServletRequest) {
        TbUser admin = (TbUser) request.getSession().getAttribute("admin");
        if(admin != null ){
        //用户已经登陆 踢回首页
            return "redirect:/main";
        }
        else{
            // 回登录 之前 查询Cookies 并把Cookies的zhi值带回login
         getUserInfoByCookies(request);
            // 用户没有登陆 回登陆
            return "login";
            }
    }



    @RequestMapping(value = {"login"}, method = RequestMethod.POST)
    @ModelAttribute
    public String postlogin(TbUser tbUser, Model model, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
       //通过
        TbUser admin = tbUserService.getAdminByemail(tbUser.getEmail(), tbUser.getPassword());

        if(admin != null){
       //用户登录成功  保存用户到session 跳转到首页
           httpServletRequest.getSession().setAttribute(Contants.Session_User,admin);

           //通过remeber 判断是否为记住我
            isSaveUserCookies(tbUser, httpServletRequest, httpServletResponse);


            return "redirect:/main";
       }
       //用户登录失败
       else {
           model.addAttribute( "message","用户或者密码错误");
           //重定向到登录
           return "redirect:/login";
       }
    }

    private void isSaveUserCookies(TbUser tbUser, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        if("on".equals(tbUser.getRememberMe())){
            // 如果为 on 储存用户信息的的 cookie
            CookiesUtil.setCookie(httpServletRequest,httpServletResponse, Contants.UserInfoCookies,tbUser.getEmail()+":"+tbUser.getPassword(),60*60*500);
        }
        else{
            //如果没有 就清空Cookies
            CookiesUtil.deleteCookie(httpServletRequest,httpServletResponse,Contants.UserInfoCookies);
        }
    }

    private void getUserInfoByCookies(HttpServletRequest request) {
        String userInfoCookie = CookiesUtil.getCookieValue(request, "UserInfoCookie");
        if(!StringUtils.isBlank(userInfoCookie)){
            String[] cookies = userInfoCookie.split(":");
            String email = cookies[0];
            String password = cookies[1];
            request.setAttribute("email", email);
            request.setAttribute("password", password);
        }
    }


}
