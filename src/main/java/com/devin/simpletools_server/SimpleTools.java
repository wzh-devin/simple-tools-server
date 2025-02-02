package com.devin.simpletools_server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.devin.simpletools_server.mapper")
public class SimpleTools {

    public static void main(String[] args) {
        SpringApplication.run(SimpleTools.class, args);
    }

}
