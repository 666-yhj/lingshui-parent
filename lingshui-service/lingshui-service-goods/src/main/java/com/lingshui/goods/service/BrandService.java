package com.lingshui.goods.service;

import com.github.pagehelper.PageInfo;
import com.lingshui.goods.pojo.Brand;

import java.util.List;

public interface BrandService {
//    查询全部
    List<Brand> findAll();
//    根据ID查询
    Brand findById(Integer id);
//    新增商品
    void add(Brand brand);
//    修改商品
    void update(Brand brand);
//    删除商品
    void delete(Integer id);
//    查询条件列表
    List<Brand> findList(Brand brand);
//    分页查询
    PageInfo<Brand> findByPage(int page,int size);
//    条件查询加分页查询
    PageInfo<Brand> findPage(Brand brand,int page,int size);

}
