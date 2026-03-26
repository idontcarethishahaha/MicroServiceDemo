package org.example.predicate;

import lombok.Data;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 类说明：自定义的年龄相关参数断言
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/3/26 18:50
 */
@Component    //断言工厂
public class AgeRoutePredicateFactory extends AbstractRoutePredicateFactory<AgeRoutePredicateFactory.Config> {
    //需要声明一个构造器，将Config类属性作为父类构造器的参数
    public AgeRoutePredicateFactory() {
        super(Config.class);
    }

    // 定义断言什么时候通过什么时候不通过
    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return serverWebExchange -> {
            // 从请求参数列表中获取 age 参数
            String ageStr = serverWebExchange.getRequest().getQueryParams().getFirst("age");
            if (ageStr == null) return false;
            int age = Integer.parseInt(ageStr);//将age参数字符串转换成整数
            return age >= config.minAge && age <= config.maxAge;
        };
    }

    // ctrl + i
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("minAge", "maxAge");
    }

    @Data
    static class Config {
        private Integer minAge;
        private Integer maxAge;
    }
    // http://localhost:14401/gateway-order-server/api/v1/start/hello?a=4&age=19

    // http://localhost:14401/gateway-order-server/api/v1/start/hello?a=4&age=15

}
