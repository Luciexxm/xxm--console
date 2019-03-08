package com.mmx.myshop.admin.dao;


import com.mmx.myshop.domain.BaseEntity;

import java.util.List;

public interface BaseTreeDao <T extends BaseEntity> {

    List<T> selectAll();

    List<T> selectByPid(long parentId);

    void insert(T t);

    void update(T t);

    void delete(long id);

    T getById(long id);

    List<Long> selectContentByCategoryId(T t);

    int selectChildrenCount(long id);
}
