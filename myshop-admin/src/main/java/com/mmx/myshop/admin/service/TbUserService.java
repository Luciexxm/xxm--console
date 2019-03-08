package com.mmx.myshop.admin.service;


import com.mmx.myshop.domain.TbUser;

public interface TbUserService extends BaseService<TbUser>{
    TbUser getAdminByemail(String email,String password);
}
