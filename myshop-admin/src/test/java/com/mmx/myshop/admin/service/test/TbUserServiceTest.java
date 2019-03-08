package com.mmx.myshop.admin.service.test;

import com.mmx.myshop.admin.dao.TbUserDao;
import com.mmx.myshop.admin.service.TbUserService;
import com.mmx.myshop.common.utils.RegexpUtils;
import com.mmx.myshop.domain.TbUser;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml", "classpath:spring-context-druid.xml", "classpath:spring-context-mybatis.xml"})
public class TbUserServiceTest {


    @Autowired
    private TbUserDao tbUserDao;
    @Autowired
    private TbUserService tbUserService;

     @Test
    public void test() {
        List<TbUser> tbUsers = tbUserDao.selectAll();

    }

   @Test
    public void insert() throws NoSuchAlgorithmException {
        TbUser tbUser = new TbUser();
        tbUser.setUsername("mmx3");
        tbUser.setCreated(new Date());
        tbUser.setEmail("mmx@163.com");
        tbUser.setPassword(DigestUtils.md5DigestAsHex("1234".getBytes()));
        tbUser.setPhone("123323123");
        tbUser.setUpdated(new Date());
        tbUserService.save(tbUser);
    }

   // @Test
    public void delete() {
        tbUserService.delete(36l);
    }

    @Test
    public void getById(){
        TbUser tb = tbUserService.getById(7l);
        System.out.println(tb.getEmail());
    }

    @Test
    public void update(){
        TbUser tbUser = tbUserService.getById(38l);

        String s = DigestUtils.md5DigestAsHex("123".getBytes());
        tbUser.setPassword(s);
        //System.out.println(tbUser.getUsername());
        tbUserService.save(tbUser);
    }

  //  @Test
    public void getByemail(){
      //  TbUser tbUser = tbUserService.getAdminByemail("mmx@163.com", "123");
        TbUser tbUser = tbUserDao.getAdminByemail("mmx@163.com");
        System.out.println(tbUser.getUsername());
    }

    @Test
    public void dataTable(){
        TbUser tbUser = new TbUser();
        tbUser.setStart(0);
        tbUser.setLength(10);
        tbUser.setUsername("m");
        List<TbUser> tbUsers = tbUserService.dataTable(tbUser);
        for (TbUser user : tbUsers) {
            user.getUsername();
        }
    }

   @Test
    public void total(){
        TbUser tbUser = new TbUser();
        tbUser.setStart(0);
        tbUser.setLength(10);
        System.out.println(tbUserService.count(tbUser));

    }

    @Test
    public void testSaveUser(){
        TbUser tbUser = new TbUser();
        tbUser.setUsername("mmx");
        tbUser.setPassword("123");
        tbUser.setEmail("mmx@2131.com");
        String s = checkUser("mmx232@163.com");
        System.out.println(s);

    }


    /**
     * 检验用户有效性
     * @return
     */
    public String checkUser(String email){
        String result = null;

     /*   if(RegexpUtils.checkPhone(phone)){
            result ="手机格式不正确";
        }*/
     if(StringUtils.isBlank(email)  || !RegexpUtils.checkEmail(email)){
            result ="邮箱格式不正确";
        }
        return result;
    }

   // @Test
    public void testcount(){
        TbUser tbUser = new TbUser();
        tbUser.setUsername("mmx2");
        int count = tbUserService.count(tbUser);
        System.out.println(count);
    }

}
