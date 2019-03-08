package com.mmx.myshop.admin.service.Impl;

import com.mmx.myshop.admin.dao.TbItemDao;
import com.mmx.myshop.admin.service.TbItemDescService;
import com.mmx.myshop.admin.service.TbItemService;
import com.mmx.myshop.domain.TbItem;
import com.mmx.myshop.domain.TbItemDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class TbItemServiceImpl extends BaseServiceImpl<TbItem,TbItemDao> implements TbItemService {

    @Autowired
    private TbItemDescService tbItemDescService;

    @Override
    @Transactional(readOnly = false)
    public void save(TbItem tbItem) {
        // 先将商品插入数据库
        super.save(tbItem);
        // 再将商品详情插入数据库
        TbItemDesc tbItemDesc = tbItem.getTbItemDesc();
        tbItemDesc.setItemId(tbItem.getId());
        tbItemDescService.insert(tbItemDesc);
    }

}
