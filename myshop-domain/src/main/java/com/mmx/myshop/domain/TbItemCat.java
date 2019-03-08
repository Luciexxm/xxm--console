package com.mmx.myshop.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=true)
public class TbItemCat extends BaseEntity implements Serializable{
    private Long parentId;
    private String name;
    private int status;
    private int sortOrder;
    @JsonProperty(value = "isParent")
    private boolean isParent;

    private  TbItemCat parentName;
}
