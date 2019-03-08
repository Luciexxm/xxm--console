package com.mmx.myshop.admin.dao;

import com.mmx.myshop.domain.TbUser;
import org.springframework.stereotype.Repository;


@Repository
public interface TbUserDao extends BaseDao<TbUser>{

    TbUser getAdminByemail(String email);
}
