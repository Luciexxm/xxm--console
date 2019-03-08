package com.mmx.myshop.admin.service.Impl;

import com.mmx.myshop.admin.dao.TbItemCatDao;
import com.mmx.myshop.admin.dao.TbItemDao;
import com.mmx.myshop.admin.service.TbItemCatService;
import com.mmx.myshop.domain.TbItemCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TbItemCatServiceImpl extends BaseTreeServiceImpl<TbItemCat,TbItemCatDao> implements TbItemCatService {

    @Autowired
    private TbItemDao tbContentDao;

    @Override
    @Transactional(readOnly = false)
    public void save(TbItemCat tbItemCat) {
        //添加根目录  该目录为父级目录
        if(tbItemCat.getParentId() == null ){
            tbItemCat.setParentId(0l);
            tbItemCat.setParent(true);
        }
        //不是根目录  该类 的 父级  设置为true
        else {
            TbItemCat parent = dao.getById(tbItemCat.getParentId());
            parent.setParent(true);
            super.save(parent);
        }
        super.save(tbItemCat);
    }


    @Override
    @Transactional(readOnly = false)
    public void delete(long id) {
        TbItemCat category = super.getById(id);
        TbItemCat parent = super.getById(category.getParentId());

        //是否为父级
        if(category.isParent()){
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
        List<TbItemCat> tbItemCats = dao.selectByPid(id);
        for (TbItemCat tbItemCat : tbItemCats) {
            tbContentDao.deleteMultiList(super.selectContentByCategoryId(tbItemCat));
            if(tbItemCat.isParent()){
                deleteMulti(tbItemCat.getId());
            }
            dao.delete(tbItemCat.getId());
        }
    }

}
