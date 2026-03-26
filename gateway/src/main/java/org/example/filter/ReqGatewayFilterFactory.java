package org.example.filter;

import lombok.Data;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Arrays;
import java.util.List;

/**
 * 类说明：练习使用Gateway过滤器
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/3/26 19:41
 */
@Component
public class ReqGatewayFilterFactory extends AbstractGatewayFilterFactory<ReqGatewayFilterFactory.Config> {

    // 定义一个静态内部类
    @Data
    static class Config{
        private Boolean requestHeader;
        private Boolean requestParam;
    }

    public ReqGatewayFilterFactory() {
        super(Config.class);
    }
    //ctrl + i


    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) ->{
            ServerHttpRequest request = exchange.getRequest();
            if(config.requestHeader){
                System.out.println("可以查看请求头中的信息！");
            }
            if(config.requestParam){
                System.out.println("允许查看请求参数的值！");
            }
            return chain.filter(exchange);
        });
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("requestHeader","requestParam");
    }
}
