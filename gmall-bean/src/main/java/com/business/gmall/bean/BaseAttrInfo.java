package com.business.gmall.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
public class BaseAttrInfo implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY) //增加注解，每次添加之后返回主键的id
    private String id;
    @Column
    private String attrName;
    @Column
    private String catalog3Id;

    //增加一个字段，表示这是一个不在数据库存在的值
    @Transient
    private List<BaseAttrValue> attrValueList;
}
