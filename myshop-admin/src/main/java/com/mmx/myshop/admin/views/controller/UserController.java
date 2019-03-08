package com.mmx.myshop.admin.views.controller;

import com.mmx.myshop.common.dto.BaseResult;
import com.mmx.myshop.common.utils.BeanValidator;
import com.mmx.myshop.admin.service.TbUserService;
import com.mmx.myshop.domain.TbUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "user")
public class UserController extends BaseController<TbUser,TbUserService>{

    @Autowired
    private TbUserService tbUserService;

    /**
     *
     * 跳转到用户列表
     * @param
     * @return
     */
    public String list(){
        return "module/user/user_list";
    }

    /**
     * 跳转到用户表单
     * @return
     */
    public String form(){
        return "module/user/user_form";
    }


    /**
     * 跳转到用户详情
     * @return
     */
    public String detail(){
        return "module/user/user_detail";
    }

    /**
     * 保存用户信息
     */
    public String save(TbUser tbUser, Model model, RedirectAttributesModelMap modelMap) {

        //校验用户信息 正确 可以保存
        String result = BeanValidator.validator(tbUser);
        if(result == null){
            tbUserService.save(tbUser);
            modelMap.addFlashAttribute("message", "保存用户信息成功");
            return "redirect:/user/list";
        }
        // 用户信息格式不正确
        else {
            modelMap.addFlashAttribute("message", result);
            return "redirect:/user/form";
        }
    }


    /**
     * 删除单个用户信息
     * @param request
     * @param modelMap
     * @return
     */
    public String delete(HttpServletRequest request, RedirectAttributesModelMap modelMap){
        Long id = Long.parseLong(request.getParameter("id"));
        tbUserService.delete(id);
        modelMap.addFlashAttribute("message","用户信息删除成功");
        return "redirect:/user/list";
    }

    /**普通
     * 批量删除
     * @param
     * @return
     */
    public String deleteMulti(HttpServletRequest request, RedirectAttributesModelMap modelMap){
        String ids = request.getParameter("ids");
        String[] idArray = ids.split(",");
        int result = tbUserService.deleteMulti(idArray);
        modelMap.addFlashAttribute("message","删除"+result+"条用户信息成功");
        return "redirect:/user/list";
    }


    /**ajax
     * 批量删除
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public BaseResult delete(String ids){
        BaseResult baseResult = null;
        //删除用户成功
        if(!StringUtils.isBlank(ids)){
            String[] idArray = ids.split(",");
            System.out.println("-----------------------------------------------------");
            System.out.println(idArray);
            tbUserService.deleteMulti(idArray);
            baseResult = BaseResult.success("用户删除成功");
        }
        //删除用户失败
        else{
            baseResult= BaseResult.fail();
        }
        return baseResult;
    }

}
