package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RocketMQUserApp {
    public static void main(String[] args) {
       SpringApplication.run(RocketMQUserApp.class, args);
       // 先运行OrderServiceTest,再运行这个
    }
}