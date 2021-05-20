package com.lingshui.item.service;

public interface PageService {

    /**
     * 根据商品Id生成静态页
     * @param spuId
     */
    public void createPageHtml(Long spuId);
}
