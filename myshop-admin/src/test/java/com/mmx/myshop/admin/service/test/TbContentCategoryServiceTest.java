package com.mmx.myshop.admin.service.test;


import com.mmx.myshop.admin.dao.TbContentDao;
import com.mmx.myshop.admin.service.TbContentCategoryService;
import com.mmx.myshop.domain.TbContentCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml", "classpath:spring-context-druid.xml", "classpath:spring-context-mybatis.xml"})
@Transactional
@Rollback
public class TbContentCategoryServiceTest {

    @Autowired
    private TbContentCategoryService tbContentCategoryService;

    @Autowired
    private TbContentDao tbContentDao;
    @Test
    public void testSelectAll() {
        tbContentCategoryService.selectAll();
    }

  // @Test
    public void testGetByPid(){
        tbContentCategoryService.selectByPid(86l);
    }

   //@Test
    public void testInsert(){
        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setName("测试2222");
        tbContentCategory.setParent(false);
        tbContentCategory.setParentId(30l);
        tbContentCategory.setStatus(1);
        tbContentCategory.setSortOrder(1);

       tbContentCategoryService.save(tbContentCategory); }

    //@Test
    public void testUpdate(){
        TbContentCategory tb = tbContentCategoryService.getById(102l);
        tb.setName("测试2333");
        tbContentCategoryService.save(tb);
    }


    //@Test
    public void tesrDelete(){
     tbContentCategoryService.delete(98l);
    }

  //  @Test
    public void tesrChildrenCount(){
        tbContentCategoryService.selectChildrenCount(86l);
    }

/*   // @Test
    public void tesrSelectContentId(){
      TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setId(147l);
        List<Long> list = tbContentCategoryService.selectContentByCategoryId(tbContentCategory);
        tbContentDao.deleteMultiList(list);
    }*/
}
