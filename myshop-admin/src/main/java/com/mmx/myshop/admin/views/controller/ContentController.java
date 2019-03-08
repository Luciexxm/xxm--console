package com.mmx.myshop.admin.views.controller;

import com.mmx.myshop.common.utils.BeanValidator;
import com.mmx.myshop.admin.service.TbContentService;
import com.mmx.myshop.domain.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "content")
public class ContentController extends BaseController<TbContent,TbContentService>{


    @Autowired
    private TbContentService tbContentService;
    @Override
    public String list() {
        return "module/content/list";
    }

    @Override
    public String form() {
        return "module/content/form";
    }

    @Override
    public String detail() {
        return "module/content/detail";
    }

    @Override
    public String save(TbContent tbContent, Model model, RedirectAttributesModelMap modelMap) {
        //校验用户信息 正确 可以保存
        String result = BeanValidator.validator(tbContent);
        if(result == null){
            tbContentService.save(tbContent);
            modelMap.addFlashAttribute("message", "保存商品信息成功");
            return "redirect:/content/list";
        }
        // 用户信息格式不正确
        else {
            modelMap.addFlashAttribute("message", result);
            return "redirect:/content/form";
        }
    }


    @Override
    public String delete(HttpServletRequest request, RedirectAttributesModelMap modelMap) {
        Long id = Long.parseLong(request.getParameter("id"));
        tbContentService.delete(id);
        modelMap.addFlashAttribute("message","商品信息删除成功");
        return "redirect:/content/list";
    }

    @Override
    public String deleteMulti(HttpServletRequest request, RedirectAttributesModelMap modelMap){
        String ids = request.getParameter("ids");
        String[] idArray = ids.split(",");
        int result = tbContentService.deleteMulti(idArray);
        modelMap.addFlashAttribute("message","删除"+result+"条用户信息成功");
        return "redirect:/content/list";
    }


}
