package com.lingshui.goods.controller;

import com.github.pagehelper.PageInfo;
import com.lingshui.goods.pojo.Brand;
import com.lingshui.goods.service.BrandService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping
    public Result<Brand> findAll() {
        List<Brand> brandList = brandService.findAll();
        return new Result<Brand>(true, StatusCode.OK, "查询成功", brandList);
    }

    @GetMapping("/{id}")
    public Result<Brand> findById(@PathVariable Integer id) {
        Brand brand = brandService.findById(id);
        return new Result<Brand>(true, StatusCode.OK, "查询成功", brand);
    }

    @PostMapping
    public Result<Brand> add(@RequestBody Brand brand){
        brandService.add(brand);
        return new Result(true,StatusCode.OK,"插入成功");
    }

    @PutMapping(value = "/{id}")
    public Result<Brand> update(@RequestBody Brand brand,@PathVariable Integer id){
        brand.setId(id);
        brandService.update(brand);
        return new Result<>(true,StatusCode.OK,"修改成功");
    }

    @DeleteMapping(value = "/{id}")
    public Result<Brand> delete(@PathVariable Integer id){
        brandService.delete(id);
        return new Result<>(true,StatusCode.OK,"删除成功");
    }

    @PostMapping(value = "/search")
    public Result<List<Brand>> findList(@RequestBody(required = false) Brand brand){
        List<Brand> list = brandService.findList(brand);
        return new Result<List<Brand>>(true,StatusCode.OK,"查询列表成功",list);
    }

    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo> findByPage(@PathVariable int page,@PathVariable int size){
//        分页查询
        PageInfo<Brand> pageInfo = brandService.findByPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    @PostMapping(value = "/search/{page}/{size}")
    public Result<PageInfo> findPage(@RequestBody(required = false) Brand brand,@PathVariable int page,@PathVariable int size){
        PageInfo<Brand> pageInfo = brandService.findPage(brand, page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询列表成功",pageInfo);
    }

}
