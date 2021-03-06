package com.lingshui.content;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
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
@MapperScan(basePackages = {"com.lingshui.content.dao"})
public class ContentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContentApplication.class);
    }
}
