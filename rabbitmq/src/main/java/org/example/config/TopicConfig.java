package org.example.config;

import jakarta.annotation.Resource;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 类说明：主题模式配置
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/4/9 20:56
 */
@Configuration
public class TopicConfig {
    @Resource
    private ConnectionFactory connectionFactory;
    private String prefix="admin.topic.";
    // RabbitAdmin 用于创建交换机和队列
    @Bean
    @ConditionalOnMissingBean//当Spring容器中已经存在这个Bean时将忽略此次注入
    public RabbitAdmin rabbitAdmin() {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);// 设置其为自启动模式
        return rabbitAdmin;
    }

    @Bean
    public Queue topicQueueA() {
        String name=prefix+"queue-a";
        Queue queue=new Queue(name, true, false, false);
        rabbitAdmin().declareQueue(queue);
        return queue;
    }

    @Bean
    public Queue topicQueueB() {
        String name=prefix+"queue-b";
        Queue queue=new Queue(name, true, false, false);
        rabbitAdmin().declareQueue(queue);
        return queue;
    }

    @Bean
    public TopicExchange topicExchange() {
        String name=prefix+"exchange";
        TopicExchange exchange=new TopicExchange(name,true,false);
        rabbitAdmin().declareExchange(exchange);
        return exchange;
    }

    @Bean
    public Binding bindTopicA(@Qualifier("topicQueueA") Queue topicQueueA,
                              @Qualifier("topicExchange") TopicExchange topicExchange) {
        return BindingBuilder.bind(topicQueueA).to(topicExchange).with("order.*");
    }

    @Bean
    public Binding bindTopicB(@Qualifier("topicQueueB") Queue topicQueueB,
                              @Qualifier("topicExchange") TopicExchange topicExchange) {
        return BindingBuilder.bind(topicQueueB).to(topicExchange).with("sale.*");
    }
}
