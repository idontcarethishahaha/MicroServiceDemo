package org.example.component;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 类说明：加载Sentinel属性配置类
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/3/26 20:29
 */
@Data
@Component
@ConfigurationProperties(prefix="sentinel.gateway")
public class SentinelGatewayProperties {
    @Data
    public static class GatewayGroup {
        private String name;//分组名
        private String pattern;//路径特征
        private int matchStrategy;//匹配策略
        private int count;//qps阈值
        private int intervalSec;//时间窗口
    }
    private List<GatewayGroup> groups;
}
