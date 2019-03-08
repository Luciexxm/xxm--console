package com.mmx.myshop.api.service.Impl;

import com.mmx.myshop.api.dao.TbContentDao;
import com.mmx.myshop.api.service.TbContentService;
import com.mmx.myshop.domain.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TbContentServiceImpl implements TbContentService {

    @Autowired
    private TbContentDao tbContentDao;

    @Override
    public List<TbContent> get(long id) {
        return null;
    }

    @Override
    public List<TbContent> ppt() {
        return tbContentDao.selectByCategoryId(103l);
    }
}
