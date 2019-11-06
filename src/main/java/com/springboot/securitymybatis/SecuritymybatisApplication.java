package com.springboot.securitymybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan({"com.springboot.securitymybatis.dao"})
public class SecuritymybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecuritymybatisApplication.class, args);
    }

}
