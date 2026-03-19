package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("org.example.feign")
@EnableDiscoveryClient
public class OpenFeignOrderApp {
    public static void main(String[] args) {
       SpringApplication.run(OpenFeignOrderApp.class, args);
    }
}