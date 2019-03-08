package com.mmx.myshop.admin.views.controller;

import com.mmx.myshop.common.utils.BeanValidator;
import com.mmx.myshop.admin.service.TbItemService;
import com.mmx.myshop.domain.TbItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpServletRequest;
@Controller
@RequestMapping(value = "item")
public class ItemCotroller extends BaseController<TbItem,TbItemService> {

    @Autowired
    private TbItemService tbItemService;

    @Override
    public String list() {
        return  "module/item/list";
    }

    @Override
    public String form() {
        return "module/item/form";
    }

    @Override
    public String detail() {
        return "module/item/detail";
    }

    @Override
    public String save(TbItem tbItem, Model model, RedirectAttributesModelMap modelMap) {   //校验用户信息 正确 可以保存
        String result = BeanValidator.validator(tbItem);
        if(result == null){
            tbItemService.save(tbItem);
            modelMap.addFlashAttribute("message", "保存商品信息成功");
            return "redirect:/item/list";
        }
        // 用户信息格式不正确
        else {
            modelMap.addFlashAttribute("message", result);
            return "redirect:/item/form";
        }
    }

    @Override
    public String delete(HttpServletRequest request, RedirectAttributesModelMap modelMap) {
        Long id = Long.parseLong(request.getParameter("id"));
        tbItemService.delete(id);
        modelMap.addFlashAttribute("message","商品信息删除成功");
        return "redirect:/item/list";
    }

    @Override
    public String deleteMulti(HttpServletRequest request, RedirectAttributesModelMap modelMap) {
        String ids = request.getParameter("ids");
        String[] idArray = ids.split(",");
        int result = tbItemService.deleteMulti(idArray);
        modelMap.addFlashAttribute("message","删除"+result+"条用户信息成功");
        return "redirect:/item/list";
    }
}
