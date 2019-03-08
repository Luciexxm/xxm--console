package com.mmx.myshop.admin.service.Impl;

import com.mmx.myshop.admin.dao.BaseTreeDao;
import com.mmx.myshop.admin.service.BaseTreeService;
import com.mmx.myshop.domain.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 树形结构 的基础业务
 * @param <T>
 * @param <D>
 */
@Transactional(readOnly = true)
public abstract  class BaseTreeServiceImpl <T extends BaseEntity,D extends BaseTreeDao<T>> implements BaseTreeService<T>{

    @Autowired
    protected D dao;

    @Override
    public List<T> selectAll() {
        return dao.selectAll();
    }

    @Override
    @Transactional(readOnly = false)
    public void save(T t) {
        //设置更新时间
        t.setUpdated(new Date());
        //新增用户
        if (t.getId() == 0) {
            t.setCreated(new Date());
            dao.insert(t);
        }
        //修改用户
        else{
            dao.update(t);
        }
    }



    @Override
    public List<T> selectByPid(long parentId) {
        return dao.selectByPid(parentId);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(long id) {
        dao.delete(id);
    }



    @Override
    public T getById(long id) {
        return dao.getById(id);
    }


    @Override
    public    List<Long> selectContentByCategoryId(T t) {
        return dao.selectContentByCategoryId(t);
    }

    @Override
    public int selectChildrenCount(long id) {
        return dao.selectChildrenCount(id);
    }
}
