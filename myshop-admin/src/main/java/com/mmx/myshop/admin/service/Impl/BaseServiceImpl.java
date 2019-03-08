package com.mmx.myshop.admin.service.Impl;

import com.mmx.myshop.admin.dao.BaseDao;
import com.mmx.myshop.admin.service.BaseService;
import com.mmx.myshop.domain.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 普通类型基础业务
 * @param <T>
 * @param <D>
 */
@Transactional(readOnly = true)
public abstract  class BaseServiceImpl <T extends BaseEntity,D extends BaseDao<T>> implements BaseService<T>{

    @Autowired
    private D dao;

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
            StringBuffer now = new StringBuffer(new SimpleDateFormat("HHmmssSSS").format(new Date()));
            Random random = new Random();
            StringBuffer append = now.append(random.nextInt(100));
            t.setId(Long.parseLong(append.toString()));
            t.setCreated(new Date());
            dao.insert(t);
        }
        //修改用户
        else{
            dao.update(t);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public T getById(Long id) {
        return dao.getById(id);
    }


    @Override
    public List<T> dataTable(T t) {
        return dao.dataTable(t);
    }

    @Override
    public Integer count(T t) {
        return dao.count(t);
    }

    @Override
    @Transactional(readOnly = false)
    public int deleteMulti(String[] ids) {
        return dao.deleteMulti(ids);
    }
}
