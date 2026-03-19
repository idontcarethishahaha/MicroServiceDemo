package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
// 作用一：将自己这个服务注册到Nacos注册中心
// 作用二：让别的服务知道我的存在，可以访问我
@EnableDiscoveryClient
public class NacosOrder1App {
    public static void main(String[] args) {
        SpringApplication.run(NacosOrder1App.class, args);
    }
}