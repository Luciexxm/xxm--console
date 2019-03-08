package com.mmx.myshop.admin.service;


import com.mmx.myshop.domain.TbItemDesc;

public interface TbItemDescService {

    void insert(TbItemDesc tbItemDesc);

    void delete(long id);

    void update(TbItemDesc tbItemDesc);
}
