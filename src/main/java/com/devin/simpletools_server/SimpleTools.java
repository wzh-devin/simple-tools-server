package com.devin.simpletools_server;

import com.alibaba.nacos.spring.context.annotation.EnableNacos;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.alibaba.nacos.spring.context.annotation.discovery.EnableNacosDiscovery;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableNacosDiscovery
@NacosPropertySource(dataId = "tools-prod.yaml", groupId = "DEFAULT_GROUP", autoRefreshed = true)
@MapperScan("com.devin.simpletools_server.mapper")
public class SimpleTools {

    public static void main(String[] args) {
        SpringApplication.run(SimpleTools.class, args);
    }

}
