package com.business.gmall.service;

import com.business.gmall.bean.*;

import java.util.List;

public interface ManageService {
    //一级分类查询接口
    List<BaseCatalog1> getAllBaseCatalog1();

    //查询所有的二级分类，根据一级分类id
    List<BaseCatalog2> getAllBaseCatalog2(String catalog1Id);
    //方法重载
    List<BaseCatalog2> getAllBaseCatalog2(BaseCatalog2 baseCatalog2);

    //查询三级分类，根据二级分类id查找
    List<BaseCatalog3> getAllBaseCatalog3(BaseCatalog3 baseCatalog3);

    //查询所有的属性信息，根据三级分类id
    List<BaseAttrInfo> getAttrInfo(BaseAttrInfo baseAttrInfo);

    //保存属性和属性值
    void saveAttrAndInfo(BaseAttrInfo baseAttrInfo);

    //修改前的查询
    List<BaseAttrValue> getAttrValue(BaseAttrValue baseAttrValue);

    //修改前的查询方法，保证查询必须是合理的状态
    BaseAttrInfo updateAttrInfo(String attrId);

    //查询所有的spuInfo信息
    List<SpuInfo> getSpuInfo(SpuInfo spuInfo);

    //加载所有的属性值列表
    List<BaseSaleAttr> getBaseSaleAttr();

    /**
     *  保存spu商品的所有相关属性
     * @param spuInfo
     */
    void saveSpuInfoObj(SpuInfo spuInfo);
}
