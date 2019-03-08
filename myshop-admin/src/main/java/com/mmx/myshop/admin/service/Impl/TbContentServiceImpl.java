package com.mmx.myshop.admin.service.Impl;

import com.mmx.myshop.admin.dao.TbContentDao;
import com.mmx.myshop.admin.service.TbContentService;
import com.mmx.myshop.domain.TbContent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TbContentServiceImpl extends BaseServiceImpl<TbContent,TbContentDao> implements TbContentService {

}
