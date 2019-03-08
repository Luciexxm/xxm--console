package com.mmx.myshop.admin.service;


import com.mmx.myshop.domain.BaseEntity;

import java.util.List;

public interface BaseTreeService <T extends BaseEntity> {

    List<T> selectAll();

    List<T> selectByPid(long parentId);

    void save(T t);

    void delete(long id);

    T getById(long id);

    List<Long> selectContentByCategoryId(T t);

    int selectChildrenCount(long id);
}
