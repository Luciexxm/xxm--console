package com.mmx.myshop.admin.service.Impl;

import com.mmx.myshop.admin.dao.TbContentCategoryDao;
import com.mmx.myshop.admin.dao.TbContentDao;
import com.mmx.myshop.admin.service.TbContentCategoryService;
import com.mmx.myshop.domain.TbContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional(readOnly = true)
public class TbContentCategoryServiceImpl extends BaseTreeServiceImpl<TbContentCategory,TbContentCategoryDao> implements TbContentCategoryService {


    @Autowired
    private TbContentDao tbContentDao;

    @Override
    @Transactional(readOnly = false)
    public void save(TbContentCategory tbContentCategory) {
    //添加根目录  该目录为父级目录
        if(tbContentCategory.getParentId() == null ){
            tbContentCategory.setParentId(0l);
        tbContentCategory.setParent(true);
    }
    //不是根目录  该类 的 父级  设置为true
        else {
            TbContentCategory parent = dao.getById(tbContentCategory.getParentId());
                parent.setParent(true);
                super.save(parent);
    }
        super.save(tbContentCategory);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(long id) {
        TbContentCategory category = super.getById(id);
        TbContentCategory parent = super.getById(category.getParentId());

        //子类数量
        int count = super.selectChildrenCount(id);
        //是否为父级
        if(  count!= 0){
            //删除子节点
            deleteMulti(id);
        }

        // 不是  根目录
        if(category.getParentId() != 0 && parent.getParentId() != 0 ){
            parent.setParent(false);
            //父级目录 设置false
            super.save(parent);
        }
        tbContentDao.deleteMultiList(super.selectContentByCategoryId(category));
        super.delete(id);
    }


    /**
     * 删除子节点  递归
     * @param id

     */
    public void deleteMulti(long id){
        List<TbContentCategory> tbContentCategories = dao.selectByPid(id);

        for (TbContentCategory tbContentCategory : tbContentCategories) {
            tbContentDao.deleteMultiList(super.selectContentByCategoryId(tbContentCategory));
            int count = dao.selectChildrenCount(tbContentCategory.getId());
            if(count != 0){
                deleteMulti(tbContentCategory.getId());
            }
            dao.delete(tbContentCategory.getId());
        }
    }

}
