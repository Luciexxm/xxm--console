package com.mmx.myshop.admin.views.controller;

import com.mmx.myshop.common.utils.BeanValidator;
import com.mmx.myshop.admin.service.TbContentCategoryService;
import com.mmx.myshop.domain.TbContentCategory;
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
@RequestMapping(value = "content/category")
public class ContentCategoryController  extends BaseTreeController<TbContentCategory,TbContentCategoryService>{

    @Autowired
    private TbContentCategoryService tbContentCategoryService;

    public String list(Model model) {
        List<TbContentCategory> tbContentCategories = tbContentCategoryService.selectAll();
        List<TbContentCategory> tagerlist = new ArrayList<>();

        sortlist(tbContentCategories,tagerlist,0l);
        model.addAttribute("tbContentCategories",tagerlist);
        return "module/content/category/list";
    }

    @Override
    public String form(TbContentCategory tbContentCategory,Model model) {
        model.addAttribute("tbContentCategory",tbContentCategory);
        return "module/content/category/form";
    }

    @Override
    public String detail() {
        return null;
    }

    @Override
    public String save(TbContentCategory tbContentCategory, Model model, RedirectAttributesModelMap modelMap) {
        //校验用户信息 正确 可以保存
        String result = BeanValidator.validator(tbContentCategory);

        if(result == null){
            tbContentCategoryService.save(tbContentCategory);
            modelMap.addFlashAttribute("message", "保存商品信息成功");
            return "redirect:/content/category/list";
        }
        // 用户信息格式不正确
        else {
            modelMap.addFlashAttribute("message", result);
            return "redirect:/content/category/form";
        }
    }

    @Override
    public String delete(HttpServletRequest request, RedirectAttributesModelMap modelMap) {
        Long id = Long.parseLong(request.getParameter("id"));
        tbContentCategoryService.delete(id);
        modelMap.addFlashAttribute("message","商品类别删除成功");
        return "redirect:/content/category/list";
    }


    @ResponseBody
    @RequestMapping(value = "dataTree",method = RequestMethod.GET)
    public List<TbContentCategory> dataTree(Long id){
        List<TbContentCategory> tbContentCategories = tbContentCategoryService.selectByPid(id == null ? 0 : id);
        return tbContentCategories;
    }

    protected void sortlist(List<TbContentCategory> sourcelist, List<TbContentCategory> tagerlist, Long parentId) {
        for (TbContentCategory tbContentCategory : sourcelist) {
            if (parentId.equals(tbContentCategory.getParentId())) {
                tagerlist.add(tbContentCategory);

                if (tbContentCategory.isParent()) {
                    for (TbContentCategory contentCategory : sourcelist) {
                        if (contentCategory.getParentId().equals(tbContentCategory.getId())) {
                            sortlist(sourcelist, tagerlist, contentCategory.getParentId());
                            break;
                        }
                    }
                }
            }
        }
    }

}
