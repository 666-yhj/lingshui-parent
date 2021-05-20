package com.lingshui.goods.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lingshui.goods.dao.BrandMapper;
import com.lingshui.goods.pojo.Brand;
import com.lingshui.goods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * 返回全部数据
     * @return
     */
    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }

    @Override
    public Brand findById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(Brand brand) {
        brandMapper.insert(brand);
    }

    @Override
    public void update(Brand brand) {
        brandMapper.updateByPrimaryKey(brand);
    }

    @Override
    public void delete(Integer id) {
        brandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Brand> findList(Brand brand) {
        Example example =createExample(brand);
        return brandMapper.selectByExample(example);
    }



    public Example createExample(Brand brand){
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if(brand!=null){
            // 品牌名称
            if(!StringUtils.isEmpty(brand.getName())){
                criteria.andLike("name","%"+brand.getName()+"%");
            }
            // 品牌图片地址
            if(!StringUtils.isEmpty(brand.getImage())){
                criteria.andLike("image","%"+brand.getImage()+"%");
            }
            // 品牌的首字母
            if(!StringUtils.isEmpty(brand.getLetter())){
                criteria.andLike("letter","%"+brand.getLetter()+"%");
            }
            // 品牌id
            if(!StringUtils.isEmpty(brand.getLetter())){
                criteria.andEqualTo("id",brand.getId());
            }
            // 排序
            if(!StringUtils.isEmpty(brand.getSeq())){
                criteria.andEqualTo("seq",brand.getSeq());
            }
        }
        return example;
    }

    @Override
    public PageInfo<Brand> findByPage(int page, int size) {
//        静态分页，PageHelper是mybatis的插件
        PageHelper.startPage(page,size);
//        分页查询
        return new PageInfo<Brand>(brandMapper.selectAll());
    }

    @Override
    public PageInfo<Brand> findPage(Brand brand, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(brand);
        return new PageInfo<Brand>(brandMapper.selectByExample(example));
    }

}
