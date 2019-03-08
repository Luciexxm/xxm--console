package com.mmx.myshop.admin.dao;


import com.mmx.myshop.domain.BaseEntity;

import java.util.List;

/**
 * 通用数据访问数据接口
 * @param <T>
 */
public interface BaseDao <T extends BaseEntity>{

    List<T> selectAll();

    void insert(T t);

    void update(T t);

    void delete(long id);

    T getById(long id);

    List<T> dataTable(T t);

    Integer count(T t);

    int deleteMulti(String[] ids);

    int deleteMultiList(List<Long> t);
}
