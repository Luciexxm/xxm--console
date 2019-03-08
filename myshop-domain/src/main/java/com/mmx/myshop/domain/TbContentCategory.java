package com.mmx.myshop.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=true)
public class TbContentCategory extends BaseEntity implements Serializable {
    private Long parentId;


    @Length(min = 1, max = 20, message = "标题长度必须介于 1 到 20 位之间")
    private String name;
    private int status;
    private int sortOrder;

    @JsonProperty(value = "isParent")
    private boolean isParent;

    private TbContentCategory parentName;

}
