package com.lingshui.goods.feign;

import com.lingshui.goods.pojo.Sku;
//import com.lingshui.order.pojo.OrderItem;
import com.lingshui.order.pojo.OrderItem;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 描述
 *
 * @author www.itheima.com
 * @version 1.0
 * @package com.lingshui.goods.feign *
 * @since 1.0
 */
@FeignClient(value="goods")
@RequestMapping("/sku")
public interface SkuFeign {
    /**
     * 查询符合条件的状态的SKU的列表
     * @param status
     * @return
     */
    @GetMapping("/status/{status}")
    Result<List<Sku>> findByStatus(@PathVariable(name = "status") String status);


    /**
     * 根据条件搜索的SKU的列表
     * @param sku
     * @return
     */
    @PostMapping(value = "/search" )
    public Result<List<Sku>> findList(@RequestBody(required = false) Sku sku);


    @GetMapping("/{id}")
    public Result<Sku> findById(@PathVariable(value = "id",required = true) Long id);



    @PostMapping(value = "/decr/count")
    public Result decrCount(@RequestBody OrderItem orderItem);



}
