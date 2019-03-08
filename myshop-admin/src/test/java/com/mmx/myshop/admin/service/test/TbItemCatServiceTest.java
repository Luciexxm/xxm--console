package com.mmx.myshop.admin.service.test;


import com.mmx.myshop.admin.service.TbItemCatService;
import com.mmx.myshop.domain.TbItemCat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml", "classpath:spring-context-druid.xml", "classpath:spring-context-mybatis.xml"})
public class TbItemCatServiceTest {
    @Autowired
    private TbItemCatService tbItemCatService;

    @Test
    public void testSelectAll() {
        tbItemCatService.selectAll();
    }

    @Test
    public void testInsert(){
        TbItemCat tbItemcat = new TbItemCat();
        tbItemcat.setName("测试2222");
        tbItemcat.setParent(false);
        tbItemcat.setParentId(30l);
        tbItemcat.setStatus(1);
        tbItemcat.setSortOrder(1);
        tbItemCatService.save(tbItemcat);
    }

}
