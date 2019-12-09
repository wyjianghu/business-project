package com.business.gmall.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
public class SpuInfo implements Serializable {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //商品所属的其他表需要与商品主键对应
    private String id;

    @Column
    private String spuName;

    @Column
    private String description;

    @Column
    private  String catalog3Id;

    /*
        分析：前端页面需要保存商品信息，图片信息list集合，商品销售属性list集合
        每个销售属性又对应了销售属性值列表。
        解决： 将所有的信息封装到本类中，统一接收
     */
    @Transient
    private List<SpuSaleAttr> spuSaleAttrList; //商品的销售属性集合，每个对象包含了值集合

    @Transient
    private List<SpuImage> spuImageList;

}
