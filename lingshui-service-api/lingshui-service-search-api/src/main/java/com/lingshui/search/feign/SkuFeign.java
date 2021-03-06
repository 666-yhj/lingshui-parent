package com.lingshui.search.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 描述
 *
 * @author www.itheima.com
 * @version 1.0
 * @package com.lingshui.search.feign *
 * @since 1.0
 */
@FeignClient(name="search")
@RequestMapping("/search")
public interface SkuFeign {
    /**
     * 搜索
     * @param searchMap
     * @return
     */
    @GetMapping
    Map search(@RequestParam(required = false) Map searchMap);
}
