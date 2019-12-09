package com.business.gmall.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class BaseSaleAttr implements Serializable { //销售属性表
    @Id
    @Column
    String id ;

    @Column
    String name;
}
