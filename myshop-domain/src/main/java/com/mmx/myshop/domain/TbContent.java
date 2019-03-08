package com.mmx.myshop.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=true)
public class TbContent extends BaseEntity implements Serializable{
   private TbContentCategory tbContentCategory;

    @Length(min = 1, max = 50, message = "标题长度必须介于 1 到 50 位之间")
    private String title;
    @Length(min = 1, max = 100, message = "标题长度必须介于 1 到 50 位之间")
    private String subTitle;
    @Length(min = 1, max = 200, message = "标题长度必须介于 1 到 50 位之间")
    private String titleDesc;
    private String url;
    private String pic;
    private String pic2;
    private String content;
}