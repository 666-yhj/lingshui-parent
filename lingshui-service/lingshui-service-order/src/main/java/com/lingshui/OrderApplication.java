package com.lingshui;

import entity.IdWorker;
import entity.TokenDecode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 描述
 *
 * @author www.itheima.com
 * @version 1.0
 * @package com.lingshui *
 * @since 1.0
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "com.lingshui.order.dao")
@EnableFeignClients(basePackages = {"com.lingshui.goods.feign","com.lingshui.user.feign"})
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    /**
     * 解析用户令牌对象
     * @return
     */
    @Bean
    public TokenDecode tokenDecode(){
        return new TokenDecode();
    }
    @Bean
    public IdWorker idWorker(){
        return new IdWorker(0,1);
    }
}
