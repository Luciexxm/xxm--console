package com.mmx.myshop.admin.service.test;


import com.mmx.myshop.admin.dao.TbContentDao;
import com.mmx.myshop.admin.service.TbContentService;
import com.mmx.myshop.domain.TbContent;
import com.mmx.myshop.domain.TbContentCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml", "classpath:spring-context-druid.xml", "classpath:spring-context-mybatis.xml"})
@Transactional
@Rollback
public class TbContentServiceTest {

    @Autowired
    private TbContentDao tbContentDao;

    @Autowired
    private TbContentService tbContentService;

   @Test
    public void test() {
        List<TbContent> tbContents = tbContentService.selectAll();
    }


   // @Test
    public void insert() {
        TbContent tbContent = new TbContent();
        tbContent.setTitle("测试标题");
        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setId(89L);
        tbContent.setTbContentCategory(tbContentCategory);
        tbContent.setSubTitle("测试子标题");
        tbContent.setTitleDesc("测试标题说明");
        tbContent.setContent("测试内容");
        tbContent.setPic("测试pic");
        tbContent.setPic("测试pic2");
        tbContent.setUrl("url");
        tbContent.setCreated(new Date());
        tbContentService.save(tbContent);
    }

  //  @Test
    public void getById(){
        TbContent tb = tbContentService.getById(32l);
        System.out.println(tb.getTitle());
    }

 //    @Test
    public void update(){
        TbContent tbContent = tbContentService.getById(32l);
        tbContent.setTitle("更新标题");
        tbContentService.save(tbContent);
    }


  //  @Test
    public void dataTable(){
        TbContent tbContent = new TbContent();
        tbContent.setStart(0);
        tbContent.setLength(10);
        tbContent.setTitle("ad");
        List<TbContent> tbUsers = tbContentService.dataTable(tbContent);
        for (TbContent tbContents : tbUsers) {
            tbContents.getTitle();
        }
    }

    //@Test
    public void delete(){
       tbContentService.delete(34l);
    }

   // @Test
    public void deleteMulti(){

        String[] s = new String [2];
        s[0] = "33";
        s[1] = "35";
        tbContentService.deleteMulti(s);
    }
    @Test
    public void page(){
        TbContent tbContent = new TbContent();
        tbContent.setStart(0);
        tbContent.setLength(10);
        tbContentService.dataTable(tbContent);
    }
}
