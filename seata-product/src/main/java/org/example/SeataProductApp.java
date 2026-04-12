package org.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "org.example.mapper")
public class SeataProductApp {
    public static void main(String[] args) {
       SpringApplication.run(SeataProductApp.class, args);
    }
}