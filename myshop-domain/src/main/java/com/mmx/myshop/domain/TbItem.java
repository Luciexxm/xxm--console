package com.mmx.myshop.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=true)
public class TbItem extends BaseEntity implements Serializable{
    @Length(min = 1, max = 20, message = "标题长度必须介于 1 到 20 位之间")
    private String title;
    private String sellPoint;
    private int price;
    private int num;
    private String barcode;
    private String image;
    private TbItemDesc tbItemDesc;
    private TbItemCat tbItemCat;
    private int status;
}
