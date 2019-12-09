package com.business.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.business.gmall.bean.BaseSaleAttr;
import com.business.gmall.bean.SpuInfo;
import com.business.gmall.service.ManageService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin //解决跨域
public class SpuManageController {

    @Reference
    private ManageService manageService;

    //查询所有的商品
    //spuList?catalog3Id=61
    @RequestMapping("spuList")
    public List<SpuInfo> getSpuInfo(SpuInfo spuInfo){
        return manageService.getSpuInfo(spuInfo);
    }

    //baseSaleAttrList
    //查询销售属性列表，就是查询字典表
    @RequestMapping("baseSaleAttrList")
    public List<BaseSaleAttr> getBaseSaleAttr(){
        return manageService.getBaseSaleAttr();
    }

    // spu商品添加,包括属性表、图片表、属性值表
    @RequestMapping("saveSpuInfo")
    public void saveSpuInfo(@RequestBody SpuInfo spuInfo){
        manageService.saveSpuInfoObj(spuInfo);
    }
}
