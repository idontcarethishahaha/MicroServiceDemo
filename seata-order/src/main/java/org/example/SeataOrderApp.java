package org.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "org.example.mapper")
@EnableFeignClients(basePackages = "org.example.feign")
public class SeataOrderApp {
    public static void main(String[] args) {
      SpringApplication.run(SeataOrderApp.class, args);
    }
}