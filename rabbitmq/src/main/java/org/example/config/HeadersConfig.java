package org.example.config;

import jakarta.annotation.Resource;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 类说明：头部模式配置类
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/4/12 10:06
 */
@Configuration
public class HeadersConfig {

    private String prefix="admin.headers.";

    @Resource
    private ConnectionFactory connectionFactory;
    // RabbitAdmin 用于创建交换机和队列
    @Bean
    @ConditionalOnMissingBean//当Spring容器中已经存在这个Bean时将忽略此次注入
    public RabbitAdmin rabbitAdmin() {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);// 设置其为自启动模式
        return rabbitAdmin;
    }

    @Bean
    public Queue headerQueueA(){
        String name = prefix+"queue-a";//队列名称
        Queue queue=new Queue(name,true,false,false);
        rabbitAdmin().declareQueue(queue);
        return queue;
    }

    @Bean
    public Queue headerQueueB(){
        String name = prefix+"queue-b";//队列名称
        Queue queue=new Queue(name,true,false,false);
        rabbitAdmin().declareQueue(queue);
        return queue;
    }

    @Bean
    public HeadersExchange headersExchange(){
        String name = prefix+"exchange";
        HeadersExchange headersExchange=new HeadersExchange(name,true,false);
        rabbitAdmin().declareExchange(headersExchange);
        return headersExchange;
    }

    // 以 Headers ALL模式绑定 queue-a
    @Bean
    public Binding bindHeaderQueueA(@Qualifier("headerQueueA") Queue queueA,
                                    @Qualifier("headersExchange") HeadersExchange headersExchange){// HashMap容量默认16
        Map<String, Object> rks = new HashMap<>(2);//rks路由键
        rks.put("a","10");
        rks.put("b","20");
        return BindingBuilder.bind(queueA).to(headersExchange).whereAll(rks).match();
    }

    // 以 Headers ANY模式绑定 queue-b
    @Bean
    public Binding bindHeaderQueueB(@Qualifier("headerQueueB") Queue queueB,
                                    @Qualifier("headersExchange") HeadersExchange headersExchange){// HashMap容量默认16
        Map<String, Object> rks = new HashMap<>(2);//rks路由键
        rks.put("c","10");
        rks.put("d","20");
        return BindingBuilder.bind(queueB).to(headersExchange).whereAny(rks).match();
    }
}
