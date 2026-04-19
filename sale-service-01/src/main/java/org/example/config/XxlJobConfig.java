package org.example.config;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 类说明：任务调度配置类
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/4/16 20:47
 */
// 任务调度中心
// http://192.168.227.128:9527/xxl-job-admin/
@Configuration
public class XxlJobConfig {
    @Value("${xxl.job.admin.addresses}")
    private String address;

    //@Value("${xxl.job.admin.accessToken}")
    @Value("twgdh")
    private String accessToken;

    //@Value("${xxl.job.admin.executor.appName}")
    @Value("sale-executor")
    private String appName;

    //@Value("${xxl.job.executor.port}")
    @Value("14891")
    private int port;

    //@Value("${xxl.job.executor.logPath}")
    @Value("./log/xxl-job")
    private String logPath;

    //@Value("${xxl.job.admin.executor.logRetentionDays}")
    @Value("30")
    private int logRetentionDays;

    @Bean
    public XxlJobSpringExecutor xxlJobSpringExecutor() {
        XxlJobSpringExecutor executor = new XxlJobSpringExecutor();
        executor.setAdminAddresses(address);
        executor.setAccessToken(accessToken);
        executor.setAppname(appName);
        executor.setLogRetentionDays(logRetentionDays);
        executor.setLogPath(logPath);
        executor.setPort(port);
        return executor;
    }
}
