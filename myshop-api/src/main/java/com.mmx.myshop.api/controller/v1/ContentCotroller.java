package com.mmx.myshop.api.controller.v1;

import com.mmx.myshop.api.dto.TbContentDTO;
import com.mmx.myshop.api.service.TbContentService;
import com.mmx.myshop.common.dto.BaseResult;
import com.mmx.myshop.domain.TbContent;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "v1/contents")
public class ContentCotroller {

    @Autowired
    private TbContentService tbContentService;

    @RequestMapping(value = "ppt",method = RequestMethod.GET)
    public BaseResult ppt(){
        List<TbContentDTO> list = new ArrayList<>();
        List<TbContent> ppt = tbContentService.ppt();

        for (TbContent tbContent : ppt) {
            TbContentDTO dto = new TbContentDTO();
            BeanUtils.copyProperties(tbContent, dto);
            list.add(dto);
        }

        return BaseResult.success(list);
    }
}
