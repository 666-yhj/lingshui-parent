package com.lingshui.canal.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.lingshui.content.feign.ContentFeign;
import com.lingshui.content.pojo.Content;
import com.lingshui.item.feign.PageFeign;
import com.xpand.starter.canal.annotation.CanalEventListener;
import com.xpand.starter.canal.annotation.ListenPoint;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

@CanalEventListener
public class CanalDataEventListener {
    @Autowired
    private ContentFeign contentFeign;
    //字符串
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //自定义数据库的 操作来监听
    //destination = "example"
    @ListenPoint(destination = "example",
            schema = "lingshui_content",
            table = {"tb_content", "tb_content_category"},
            eventType = {
                    CanalEntry.EventType.UPDATE,
                    CanalEntry.EventType.DELETE,
                    CanalEntry.EventType.INSERT})
    public void onEventCustomUpdate(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        //1.获取列名 为category_id的值
        String categoryId = getColumnValue(eventType, rowData);
        //2.调用feign 获取该分类下的所有的广告集合
        Result<List<Content>> categoryresut = contentFeign.findByCategory(Long.valueOf(categoryId));
        List<Content> data = categoryresut.getData();
        //3.使用redisTemplate存储到redis中
        stringRedisTemplate.boundValueOps("content_" + categoryId).set(JSON.toJSONString(data));
    }

    private String getColumnValue(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        String categoryId = "";
        //判断 如果是删除  则获取beforlist
        if (eventType == CanalEntry.EventType.DELETE) {
            for (CanalEntry.Column column : rowData.getBeforeColumnsList()) {
                if (column.getName().equalsIgnoreCase("category_id")) {
                    categoryId = column.getValue();
                    return categoryId;
                }
            }
        } else {
            //判断 如果是添加 或者是更新 获取afterlist
            for (CanalEntry.Column column : rowData.getAfterColumnsList()) {
                if (column.getName().equalsIgnoreCase("category_id")) {
                    categoryId = column.getValue();
                    return categoryId;
                }
            }
        }
        return categoryId;
    }

    //监听商品数据库的spu的表的数据的变化,变了,调用feign 生成静态页就可以了
    @Autowired
    private PageFeign pageFeign;

    @ListenPoint(destination = "example",
                schema = "lingshui_goods",
                table = {"tb_spu"},
                eventType = {CanalEntry.EventType.UPDATE, CanalEntry.EventType.INSERT, CanalEntry.EventType.DELETE})
    public void onEventCustomSpu(CanalEntry.EventType eventType, CanalEntry.RowData rowData){

        //        判断操作类型
        if(eventType==CanalEntry.EventType.DELETE) {
            String spuId = "";
            List<CanalEntry.Column> beforeColumnsList = rowData.getBeforeColumnsList();
            for (CanalEntry.Column column : beforeColumnsList) {
                if (column.getName().equals("id")) {
                    spuId = column.getValue();
                    break;
                }
            }
//            删除静态页
        }else{
//            新增 更新
            List<CanalEntry.Column> afterColumnsList = rowData.getBeforeColumnsList();
            String spuId = "";
            for (CanalEntry.Column column : afterColumnsList) {
                if(column.getName().equals("id")){
                    spuId=column.getValue();
                    break;
                }
            }
//            更新 生成静态页
            pageFeign.createHtml(Long.valueOf(spuId));
        }
    }
}
