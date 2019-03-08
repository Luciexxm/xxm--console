package com.mmx.myshop.admin.service;


import com.mmx.myshop.domain.BaseEntity;

import java.util.List;

public    interface BaseService <T extends BaseEntity>{
    /**
     * 查询全部数据
     * @return
     */
    List<T> selectAll();

    /**
     *  保存数据
     * @param t
     * @return
     */
    void save(T t);


    /**
     *通过id删除用户
     * @param id
     */
    void delete(Long id);

    /**
     * t通过id 获取用户信息
     * @param id
     * @return
     */
    T getById(Long id);


    /**
     * 分页查询
     * @param t
     * @return
     */
    List<T> dataTable(T t);

    /**
     * 查询用户数据数量
     * @param t
     * @return
     */
    Integer count(T t);


    /**
     * 批量删除用户
     */
    int deleteMulti(String[] ids);
}
