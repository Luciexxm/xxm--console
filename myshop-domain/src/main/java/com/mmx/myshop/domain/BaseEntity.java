package com.mmx.myshop.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public abstract class BaseEntity implements Serializable {
    private long id;
    private int start;
    private int length;


    private Date updated;
    private Date created;

}
