package com.mmx.myshop.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TbItemDesc implements Serializable {
    private long itemId;
    private String itemDesc;
    private Date created;
    private Date updated;
}
