package org.example.config;

import jakarta.annotation.Resource;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 类说明：配置广播模式
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/4/9 20:24
 */
@Configuration
public class FanoutConfig {
    @Resource
    private ConnectionFactory connectionFactory;
    private String prefix="admin.fanout.";
    // RabbitAdmin 用于创建交换机和队列
    @Bean
    @ConditionalOnMissingBean//当Spring容器中已经存在这个Bean时将忽略此次注入
    public RabbitAdmin rabbitAdmin() {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);// 设置其为自启动模式
        return rabbitAdmin;
    }

    @Bean
    public Queue fanoutQueueA() {
        String name=prefix+"queue-a";
        Queue queue=new Queue(name, true, false, false);
        rabbitAdmin().declareQueue(queue);
        return queue;
    }

    @Bean
    public Queue fanoutQueueB() {
        String name=prefix+"queue-b";
        Queue queue=new Queue(name, true, false, false);
        rabbitAdmin().declareQueue(queue);
        return queue;
    }

    @Bean
    public Queue fanoutQueueC() {
        String name=prefix+"queue-c";
        Queue queue=new Queue(name, true, false, false);
        rabbitAdmin().declareQueue(queue);
        return queue;
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        String name = prefix+"exchange";
        FanoutExchange exchange=new FanoutExchange(name,true,false);
        rabbitAdmin().declareExchange(exchange);
        return exchange;
    }
    //广播模式，需要将队列和交换机分别依次绑定，无须指定路由键
    @Bean
    public Binding bindingQueueA(@Qualifier("fanoutQueueA") Queue fanoutQueueA,
                                 @Qualifier("fanoutExchange") FanoutExchange fanoutExchange) {
        // 广播模式无需指定路由键
        return BindingBuilder.bind(fanoutQueueA).to(fanoutExchange);
    }

    @Bean
    public Binding bindingQueueB(@Qualifier("fanoutQueueB") Queue fanoutQueueB,
                                 @Qualifier("fanoutExchange") FanoutExchange fanoutExchange) {
        // 广播模式无需指定路由键
        return BindingBuilder.bind(fanoutQueueB).to(fanoutExchange);
    }

    @Bean
    public Binding bindingQueueC(@Qualifier("fanoutQueueC") Queue fanoutQueueC,
                                 @Qualifier("fanoutExchange") FanoutExchange fanoutExchange) {
        // 广播模式无需指定路由键
        return BindingBuilder.bind(fanoutQueueC).to(fanoutExchange);
    }
}
