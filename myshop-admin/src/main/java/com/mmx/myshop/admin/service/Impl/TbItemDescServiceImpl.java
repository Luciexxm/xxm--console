package com.mmx.myshop.admin.service.Impl;

import com.mmx.myshop.admin.dao.TbItemDescDao;
import com.mmx.myshop.admin.service.TbItemDescService;
import com.mmx.myshop.domain.TbItemDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(readOnly = true)
public class TbItemDescServiceImpl implements TbItemDescService{

  @Autowired
  private TbItemDescDao tbItemDescDao;


  @Override
  @Transactional(readOnly = false)
  public void insert(TbItemDesc tbItemDesc) {
      tbItemDesc.setCreated(new Date());

      tbItemDescDao.insert(tbItemDesc);
  }

  @Override
  @Transactional(readOnly = false)
    public void delete(long id) {
      tbItemDescDao.delete(id);
    }

  @Override
  @Transactional(readOnly = false)
  public void update(TbItemDesc tbItemDesc) {
    tbItemDesc.setUpdated(new Date());
    tbItemDescDao.update(tbItemDesc);
  }


}
