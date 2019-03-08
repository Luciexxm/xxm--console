package com.mmx.myshop.admin.service.test;

import com.mmx.myshop.admin.service.TbItemService;
import com.mmx.myshop.domain.TbItem;
import com.mmx.myshop.domain.TbItemCat;
import com.mmx.myshop.domain.TbItemDesc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml", "classpath:spring-context-druid.xml", "classpath:spring-context-mybatis.xml"})
public class TbItemServiceTest {

    @Autowired
    private TbItemService itemService;

    @Test
    public void testselectAll() {
        itemService.selectAll();
    }

    @Test
    public void testInsert() {
        TbItem tbItem = new TbItem();
        tbItem.setId(0l);
        tbItem.setTitle("测试标题");
        tbItem.setSellPoint("测试卖点");
        tbItem.setNum(9999);
        tbItem.setStatus(1);
        tbItem.setImage("123.jpg");
        tbItem.setPrice(266666);

        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(tbItem.getId());
        tbItemDesc.setItemDesc("测试卖点描述");
        tbItem.setTbItemDesc(tbItemDesc);

        TbItemCat tbItemCat = new TbItemCat();
        tbItemCat.setId(560l);
        tbItem.setTbItemCat(tbItemCat);

        itemService.save(tbItem);
    }


    @Test
    public void testgetById() {
        itemService.getById(0l);

    }
    @Test
    public void testupdate() {
        TbItem tbItem = itemService.getById(1l);
        tbItem.setTitle("修改测试标题");
        itemService.save(tbItem);
    }

    @Test
    public void testDelete() {
        itemService.delete(1l);
    }

    @Test
    public void testcount() {
        TbItem tbItem = new TbItem();
        tbItem.setStart(0);
        tbItem.setLength(7);
        itemService.count(tbItem);
    }
}
