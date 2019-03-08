package com.mmx.myshop.admin.service.test;

import com.mmx.myshop.admin.service.TbItemDescService;
import com.mmx.myshop.domain.TbItemDesc;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml", "classpath:spring-context-druid.xml", "classpath:spring-context-mybatis.xml"})
@Transactional
@Rollback
public class TbItemDescServiceTest {

    @Autowired
    private TbItemDescService tbItemDescService;


   // @Test
 /*   public void testInsert() {
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemDesc("123");
        tbItemDesc.setItemId(536564l);
        tbItemDesc.setCreated(new Date());
        tbItemDesc.setUpdated(new Date());
        tbItemDescService.insert(tbItemDesc);
    }
*/
}
