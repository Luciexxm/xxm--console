package com.mmx.myshop.admin.views.controller;

import com.mmx.myshop.admin.service.BaseService;
import com.mmx.myshop.domain.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public  abstract  class BaseController<T extends BaseEntity, S extends BaseService> {

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

    /**
     * ajax dataTable分页查询 展示
     * @param t
     * @param httpServletRequest
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="page", method = RequestMethod.GET)
    public Map<String, Object> page(T t, HttpServletRequest httpServletRequest){
        Map<String, Object> result = new HashMap<>();
        String strdraw = httpServletRequest.getParameter("draw");
        String strstart = httpServletRequest.getParameter("start");
        String strlength = httpServletRequest.getParameter("length");
        int draw = strdraw == null ? 0:Integer.parseInt(strdraw);
        int start = strstart == null ? 0:Integer.parseInt(strstart);
        int length = strlength == null ? 10:Integer.parseInt(strlength);


        List<T> ts = service.dataTable(t);
        int count = service.count(t);
        result.put("draw", draw);
        result.put("recordsTotal",count);
        result.put("recordsFiltered",count);
        result.put("data",ts);
        result.put("error","");
        return result;
    }


    @RequestMapping(value = "list",method = RequestMethod.GET)
    public abstract String list();

    @RequestMapping(value = "form",method = RequestMethod.GET)
    public abstract String form();


    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public abstract String detail();

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public abstract String save(T t , Model model, RedirectAttributesModelMap modelMap);

    @RequestMapping(value = "delete",method = RequestMethod.GET)
    public abstract String delete(HttpServletRequest request, RedirectAttributesModelMap modelMap);

    @RequestMapping(value = "deleteMulti",method = RequestMethod.GET)
    public abstract String deleteMulti(HttpServletRequest request, RedirectAttributesModelMap modelMap);

}
