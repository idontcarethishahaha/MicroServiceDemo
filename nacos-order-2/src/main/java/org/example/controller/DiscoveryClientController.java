package org.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 类说明：
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/3/15 11:03
 */
@RestController
@RequestMapping("api/v1/order")
public class DiscoveryClientController {
    // 可以通过 DiscoveryClient 实例获取注册中心的注册列表数据信息
    @Autowired
    private DiscoveryClient discoveryClient;

    //   http://localhost:14101/api/v1/order/listInstances
    @GetMapping("listInstances")
    public List<ServiceInstance> list() {
        return discoveryClient.getInstances("nacos-order");
    }

}
