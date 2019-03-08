package com.mmx.myshop.admin.dao;

import com.mmx.myshop.domain.TbItemDesc;
import org.springframework.stereotype.Repository;

@Repository
public interface TbItemDescDao {
    void insert(TbItemDesc tbItemDesc);

    void delete(long id);

    void update(TbItemDesc tbItemDesc);
}
