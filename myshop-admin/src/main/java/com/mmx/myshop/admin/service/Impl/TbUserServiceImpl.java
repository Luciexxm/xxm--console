package com.mmx.myshop.admin.service.Impl;

import com.mmx.myshop.admin.dao.TbUserDao;
import com.mmx.myshop.admin.service.TbUserService;
import com.mmx.myshop.domain.TbUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

/**
 *   用户业务
*@Description:
*@Param:
*@return:
*@Author: mmx
*@date:
*/
@Service
@Transactional(readOnly = true)
public class TbUserServiceImpl extends BaseServiceImpl<TbUser,TbUserDao> implements TbUserService {

    private static final Logger logger = LoggerFactory.getLogger(TbUserServiceImpl.class);

    @Autowired
    public TbUserDao tbUserDao;
    /**
     * 验证登录
     * 通过邮箱获取 admin
     * @param password
     * @param email
     * @return
     */
    @Override
    public TbUser getAdminByemail(String email, String password) {
        TbUser tbUser = null;
        tbUser = tbUserDao.getAdminByemail(email);
        String Md5passowrd = DigestUtils.md5DigestAsHex(password.getBytes());
        if (tbUser != null) {
            //密码正确
            if (Md5passowrd.equals(tbUser.getPassword())) {
                return tbUser;
            }
        }
                return null;
    }

}
