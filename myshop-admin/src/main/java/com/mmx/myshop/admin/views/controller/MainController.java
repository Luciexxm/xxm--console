package com.mmx.myshop.admin.views.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @program: myshop
 * @description: 登录首页
 * @author: mmx
 * @create: 2018-08-31 21:55
 */
@Controller
public class MainController {

    @RequestMapping(value ={"main"},method = RequestMethod.GET)
    public String  main(){
      return "main";
    }

}
