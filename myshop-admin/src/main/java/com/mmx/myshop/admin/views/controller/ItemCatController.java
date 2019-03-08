package com.mmx.myshop.admin.views.controller;

import com.mmx.myshop.common.utils.BeanValidator;
import com.mmx.myshop.admin.service.TbItemCatService;
import com.mmx.myshop.domain.TbItemCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "item/cat")
public class ItemCatController extends BaseTreeController<TbItemCat,TbItemCatService>{

    @Autowired
    private TbItemCatService tbItemCatService;

    public String list(Model model) {
        List<TbItemCat> tbItemCats = tbItemCatService.selectAll();
        List<TbItemCat> tagerlist = new ArrayList<>();
        sortlist(tbItemCats,tagerlist,0l);
        model.addAttribute("tbItemCats",tagerlist);
        return "module/item/cat/list";
    }


    @Override
    public String form(TbItemCat tbItemCat,Model model) {
        model.addAttribute("tbItemCat",tbItemCat);
        return "module/item/cat/form";
    }

    @Override
    public String detail() {
        return null;
    }

    @Override
    public String save(TbItemCat tbItemCat, Model model, RedirectAttributesModelMap modelMap) {
        //校验用户信息 正确 可以保存
        String result = BeanValidator.validator(tbItemCat);

        if(result == null){
            tbItemCatService.save(tbItemCat);
            modelMap.addFlashAttribute("message", "保存商品信息成功");
            return "redirect:/item/cat/list";
        }
        // 用户信息格式不正确
        else {
            modelMap.addFlashAttribute("message", result);
            return "redirect:/item/cat/form";
        }
    }

    @Override
    public String delete(HttpServletRequest request, RedirectAttributesModelMap modelMap) {
        Long id = Long.parseLong(request.getParameter("id"));
        tbItemCatService.delete(id);
        modelMap.addFlashAttribute("message","商品类别删除成功");
        return "redirect:/item/cat/list";
    }


    @ResponseBody
    @RequestMapping(value = "dataTree",method = RequestMethod.GET)
    public List<TbItemCat> dataTree(Long id){
        List<TbItemCat> tbItemCats = tbItemCatService.selectByPid(id == null ? 0 : id);
        return tbItemCats;
    }

    protected void sortlist(List<TbItemCat> sourcelist, List<TbItemCat> tagerlist, Long parentId) {
        for (TbItemCat tbItemCat : sourcelist) {
            if (parentId.equals(tbItemCat.getParentId())) {
                tagerlist.add(tbItemCat);

                if (tbItemCat.isParent()) {
                    for (TbItemCat itemCat : sourcelist) {
                        if (itemCat.getParentId().equals(tbItemCat.getId())) {
                            sortlist(sourcelist, tagerlist, itemCat.getParentId());
                            break;
                        }
                    }
                }
            }
        }
    }

}
