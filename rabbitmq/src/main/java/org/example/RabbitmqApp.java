package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitmqApp {
    public static void main(String[] args) {
        SpringApplication.run(RabbitmqApp.class, args);
    }
}
//http://192.168.227.128:15672/
// 客户端访问端口是5672
/*
docker run --name rabbit --network my-net \
      -p 5672:5672 \
      -p 15672:15672 \
      -p 15692:15692 \
      -e RABBITMQ_DEFAULT_USER=admin \
      -e RABBITMQ_DEFAULT_PASS=admin \
      -d registry.cn-hangzhou.aliyuncs.com/joezhou/rabbitmq:3.9.11;
 */