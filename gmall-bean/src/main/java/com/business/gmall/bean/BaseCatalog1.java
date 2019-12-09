package com.business.gmall.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class BaseCatalog1 implements Serializable{
    @Id
    @Column
    private String id; //数据库中的字段为bigint
    @Column
    private String name;
}
