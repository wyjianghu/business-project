package com.business.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.business.gmall.bean.*;
import com.business.gmall.service.ManageService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin //解决跨域
public class ManageController {

    @Reference
    private ManageService manageService;

    //获取一级分类
    @RequestMapping("getCatalog1")
    public List<BaseCatalog1> getCatalog1(){
        return manageService.getAllBaseCatalog1();
    }
    //获取二级分类id,getCatalog2?catalog1Id=1
    @RequestMapping("getCatalog2")
    public List<BaseCatalog2> getCatalog2(String catalog1Id){
       List<BaseCatalog2> baseCatalog2List = manageService.getAllBaseCatalog2(catalog1Id);
       return baseCatalog2List;
    }
//    //重载方法
//    @RequestMapping("getCatalog2")
//    public List<BaseCatalog2> getCatalog2(BaseCatalog2 baseCatalog2){
//        return  manageService.getAllBaseCatalog2(baseCatalog2);
//    }
    //获取三级分类，通过二级分类id,getCatalog3?catalog2Id=1
    @RequestMapping("getCatalog3")
    public List<BaseCatalog3> getCatalog3(BaseCatalog3 baseCatalog3){
       return manageService.getAllBaseCatalog3(baseCatalog3);
    }

    //获取属性和属性值attrInfoList?catalog3Id=107l
    @RequestMapping("attrInfoList")
    public List<BaseAttrInfo> getAttrInfo(BaseAttrInfo baseAttrInfo){
        return manageService.getAttrInfo(baseAttrInfo);
    }


    /*
        添加属性：saveAttrInfo
        分析：属性和属性值在不同的两张表里边
              属性是一个对象，但是属性值是一个list集合
        解决：改变属性的bean，增加接收属性值的集合
     */
    @RequestMapping("saveAttrInfo") //这里的修改和保存是一个方法，公用一个方法
    public void saveAttrAndInfo(@RequestBody BaseAttrInfo baseAttrInfo){
        manageService.saveAttrAndInfo(baseAttrInfo);
    }

    //修改前的查询方法，getAttrValueList?attrId=111
//    @RequestMapping("getAttrValueList")
//    public List<BaseAttrValue> getAttrValueList(BaseAttrValue baseAttrValue){
//        return manageService.getAttrValue(baseAttrValue);
//    }

    //getAttrValueList?attrId=111
    @RequestMapping("getAttrValueList") //修改之前先查询有没有属性，如果有再获取属性值
    public List<BaseAttrValue> getAttrValueList(String attrId){
        BaseAttrInfo baseAttrInfo = manageService.updateAttrInfo(attrId);
        return baseAttrInfo.getAttrValueList();
    }

}
