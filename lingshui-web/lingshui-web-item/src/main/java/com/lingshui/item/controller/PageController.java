package com.lingshui.item.controller;

import com.lingshui.item.service.PageService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

public class PageController {

    @Autowired
    private PageService pageService;

    /**
     * 生成静态页面
     * @param id
     * @return
     */
    public Result createPageHtml(@PathVariable(name = "id") Long id){
        pageService.createPageHtml(id);
        return new Result(true, StatusCode.OK,"ok");
    }
}
