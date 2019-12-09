package com.business.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.business.gmall.bean.*;
import com.business.gmall.exception.MySelfException;
import com.business.gmall.manage.mapper.*;
import com.business.gmall.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ManageServiceImpl implements ManageService {

    //注入一级分类的mapper
    @Autowired
    private BaseCatalog1Mapper baseCatalog1Mapper;
    //注入2级分类
    @Autowired
    private BaseCatalog2Mapper baseCatalog2Mapper;
    //注入3级分类
    @Autowired
    private BaseCatalog3Mapper baseCatalog3Mapper;
    //注入商品属性
    @Autowired
    private BaseAttrInfoMapper baseAttrInfoMapper;
    //注入商品的属性值
    @Autowired
    private BaseAttrValueMapper baseAttrValueMapper;
    //注入spuInfo商品信息
    @Autowired
    private SpuInfoMapper spuInfoMapper;
    //注入spu商品销售属性mapper(字典表)
    @Autowired
    private BaseSaleAttrMapper baseSaleAttrMapper;
    //注入spu商品的销售属性mapper
    @Autowired
    private SpuSaleAttrMapper spuSaleAttrMapper;
    //注入spu商品的销售属性值mapper
    @Autowired
    private SpuSaleAttrValueMapper spuSaleAttrValueMapper;
    //注入商品的image图片属性
    @Autowired
    private SpuImageMapper spuImageMapper;



    //一级分类
    @Override
    public List<BaseCatalog1> getAllBaseCatalog1() {
        return baseCatalog1Mapper.selectAll();
    }

    //获取二级分类
    @Override
    public List<BaseCatalog2> getAllBaseCatalog2(String catalog1Id) {
        BaseCatalog2 baseCatalog2 = new BaseCatalog2();
        baseCatalog2.setCatalog1Id(catalog1Id);
        return baseCatalog2Mapper.select(baseCatalog2);
    }
    //方法重载
    @Override
    public List<BaseCatalog2> getAllBaseCatalog2(BaseCatalog2 baseCatalog2) {
        return baseCatalog2Mapper.select(baseCatalog2);
    }

    //获取三级分类
    @Override
    public List<BaseCatalog3> getAllBaseCatalog3(BaseCatalog3 baseCatalog3) {
        return baseCatalog3Mapper.select(baseCatalog3);
    }

    //属性值的查询
    @Override
    public List<BaseAttrInfo> getAttrInfo(BaseAttrInfo baseAttrInfo) {
        return baseAttrInfoMapper.select(baseAttrInfo);
    }

    //这里保存属性和属性值
    @Override
    @Transactional
    public void saveAttrAndInfo(BaseAttrInfo baseAttrInfo) {
        //判断是保存还是修改
//        String baiId = baseAttrInfo.getId(); //这个值在后期会变化
        if(baseAttrInfo.getId() != null && baseAttrInfo.getId().length()>0){
            //这是修改方法
            baseAttrInfoMapper.updateByPrimaryKeySelective(baseAttrInfo);
        }else{
            //这是保存方法
            //baseAttrInfo 对象包含了属性的值和属性值的
            //保存属性
            baseAttrInfoMapper.insertSelective(baseAttrInfo);
        }


        //这里根据前端页面的情况，可以在每次添加之前先删除
        BaseAttrValue baseValueDel = new BaseAttrValue();
        baseValueDel.setAttrId(baseAttrInfo.getId());
        baseAttrValueMapper.delete(baseValueDel);

        //判断是不是有集合，并且集合的长度大于0
        //重新插入属性值
        if(baseAttrInfo.getAttrValueList()!=null && baseAttrInfo.getAttrValueList().size()>0) {
            for (BaseAttrValue attrValue : baseAttrInfo.getAttrValueList()) {
                attrValue.setId(null);
                attrValue.setAttrId(baseAttrInfo.getId());
                baseAttrValueMapper.insertSelective(attrValue);
            }
        }

    }

    //根据id直接查询的修改方法
    @Override
    public List<BaseAttrValue> getAttrValue(BaseAttrValue baseAttrValue) {
        List<BaseAttrValue> listBav = baseAttrValueMapper.select(baseAttrValue);
//        System.out.println(listBav);
        return listBav;
    }

    //根据属性查属性值
    @Override
    public BaseAttrInfo updateAttrInfo(String attrId) {
        //获取属性
        BaseAttrInfo baseAttrInfo = baseAttrInfoMapper.selectByPrimaryKey(attrId);

        //如果存在属性则查询修改
        if(baseAttrInfo != null){
            //创建对象,查询属性值
            BaseAttrValue baseAttrValue = new BaseAttrValue();
            baseAttrValue.setAttrId(attrId);
            //查询
            List<BaseAttrValue> attrValues = baseAttrValueMapper.select(baseAttrValue);

            //赋值给BaseAttrInfo对象
            baseAttrInfo.setAttrValueList(attrValues);
            return baseAttrInfo;
        }else{
            throw new MySelfException(20000,"该属性不存在，不可以查询属性值"); //这里应该不能查询到结果，抛自定义的异常
        }
    }

    //获取所有的spuInfo信息
    @Override
    public List<SpuInfo> getSpuInfo(SpuInfo spuInfo) {
        return spuInfoMapper.select(spuInfo);
    }

    // 获取属性字典表
    @Override
    public List<BaseSaleAttr> getBaseSaleAttr() {
        return baseSaleAttrMapper.selectAll();
    }

    // 保存属性的所有信息
    @Override
    @Transactional //这里需要添加事务控制，要么同时成功，要么同时失败
    public void saveSpuInfoObj(SpuInfo spuInfo) {
        // 1、保存spu商品的数据
        spuInfoMapper.insertSelective(spuInfo);

        //2、保存spu商品的img信息
        List<SpuImage> spuImageList = spuInfo.getSpuImageList();
        boolean exitSpuInfo = this.ListIsEmpty(spuImageList);
        if (exitSpuInfo){ //true表示集合不为空
            for (SpuImage spuImage : spuImageList) {
                //设置 spuId
                spuImage.setSpuId(spuInfo.getId());
                //保存商品图片信息
                spuImageMapper.insertSelective(spuImage);
            }
        }


        //3、保存spu商品的属性
        List<SpuSaleAttr> spuSaleAttrList = spuInfo.getSpuSaleAttrList();
        boolean exitSpuAttr = this.ListIsEmpty(spuSaleAttrList);
        if(exitSpuAttr){
            for (SpuSaleAttr spuSaleAttr : spuSaleAttrList) {
                //设置spuId
                spuSaleAttr.setSpuId(spuInfo.getId());
                //保存商品属性数据
                spuSaleAttrMapper.insertSelective(spuSaleAttr);

                //4、每一个商品属性包含list集合的属性值
                List<SpuSaleAttrValue> spuSaleAttrValueList = spuSaleAttr.getSpuSaleAttrValueList();
                boolean exitSpuAttrValue = this.ListIsEmpty(spuSaleAttrValueList);
                if(exitSpuAttrValue){
                    for (SpuSaleAttrValue spuSaleAttrValue : spuSaleAttrValueList) {
                        //设置spuId
                        spuSaleAttrValue.setSpuId(spuInfo.getId());
                        //保存商品的属性值数据
                        spuSaleAttrValueMapper.insertSelective(spuSaleAttrValue);
                    }
                }
            }
        }
    }

    //封装一个方法，判断集合不为空
    private <T> boolean ListIsEmpty(List<T> list){
        if(list != null && list.size()>0){
            return true;
        }
        return false;
    }
}
