package com.mmx.myshop.admin.views.controller;

import com.mmx.myshop.admin.service.BaseTreeService;
import com.mmx.myshop.domain.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.ParameterizedType;

public abstract class BaseTreeController<T extends BaseEntity,S extends BaseTreeService> {
    @Autowired
    private S service;

    /**
     * 是新增还是编辑
     * 提交时 保存用户信息 不被清空
     * @param id
     * @return tbUser
     */
    @ModelAttribute
    public T getEntity(String id){
        T t = null;
        if(id == null || "0".equals(id) ){
            try {
                ParameterizedType ptype = (ParameterizedType) this.getClass().getGenericSuperclass();
                Class clazz = (Class<T>) ptype.getActualTypeArguments()[0];
                t = (T) clazz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        //没有id 需要新建因为 form不能为空
        else {
            t = (T) service.getById(Long.parseLong(id));
        }
        return  t;
    }

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public abstract String list(Model model);

    @RequestMapping(value = "form",method = RequestMethod.GET)
    public abstract String form(T t,Model model);


    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public abstract String detail();

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public abstract String save(T t , Model model, RedirectAttributesModelMap modelMap);

    @RequestMapping(value = "delete",method = RequestMethod.GET)
    public abstract String delete(HttpServletRequest request, RedirectAttributesModelMap modelMap);

}
