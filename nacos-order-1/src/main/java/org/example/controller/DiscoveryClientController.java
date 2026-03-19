package org.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 类说明：
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/3/15 11:03
 */
@RestController
@RequestMapping("api/v1/order")
@RefreshScope// 实时刷新Nocas配置文件
public class DiscoveryClientController {
    @Value("${server.port}")
    private int port;
    @Value("${env.name}")
    private String envName;
    @Value("${env.location}")
    private String envLocation;

    // 可以通过 DiscoveryClient 实例获取注册中心的注册列表数据信息
    @Autowired
    private DiscoveryClient discoveryClient;

    //   http://localhost:14101/api/v1/order/listInstances
    @GetMapping("listInstances")
    public List<ServiceInstance> list() {
        return discoveryClient.getInstances("nacos-order");
    }

    //test  http://localhost:14104/api/v1/order/test-config
    //prod  http://localhost:14105/api/v1/order/test-config
    //dev  http://localhost:14106/api/v1/order/test-config
    @GetMapping("/test-config")
    public Map testConfig() {
        return Map.of("port:",port,"envName:",envName,"envLocation:",envLocation);
    }

}
