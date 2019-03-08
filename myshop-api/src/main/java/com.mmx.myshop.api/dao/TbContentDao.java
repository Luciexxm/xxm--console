package com.mmx.myshop.api.dao;

import com.mmx.myshop.domain.TbContent;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbContentDao {
    List<TbContent> get(long id);

    /**
     * 根据  类别id 选择内容
     * @param id
     * @return
     */
    List<TbContent>selectByCategoryId(long id);

}
