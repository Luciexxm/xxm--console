package com.mmx.myshop.api.service;

import com.mmx.myshop.domain.TbContent;

import java.util.List;

public interface TbContentService {

    List<TbContent> get(long id);

    List<TbContent>ppt();
}
