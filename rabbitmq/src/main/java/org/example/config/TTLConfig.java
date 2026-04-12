package org.example.config;

import jakarta.annotation.Resource;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 类说明：延迟消息+死信队列
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/4/12 10:59
 */
// https://blog.csdn.net/CSDN_JOEZHOU/article/details/150521064
@Configuration
public class TTLConfig {
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

    private final String prefix="admin.ttl";
    // 声明死信队列：专门存放超时未消费火队列满之后被移除的消息
   @Bean
    public DirectExchange directExchangeTTL() { // 延迟交换机
        String exchangeName = prefix+"ttl-exchange";
        DirectExchange directExchange
                = ExchangeBuilder.directExchange(exchangeName)
                .durable(true)
                .build();
        rabbitAdmin().declareExchange(directExchange);
        return directExchange;
   }

   @Bean
    public DirectExchange directExchangeTimeout() {//超时交换机：负责将超时消息放入死信队列
       String exchangeName = prefix+"timeout-exchange";
       DirectExchange directExchange
               = ExchangeBuilder.directExchange(exchangeName)
               .durable(true)
               .build();
       rabbitAdmin().declareExchange(directExchange);
       return directExchange;
   }

   @Bean
    public Queue ttlQueue() {// 用来存放延迟消息的队列，这里的消息都有 ttl 时间
       String name = prefix+"ttl-queue";//队列名称
       String routingKey = prefix+"order";//路由键
       String timeoutExchange =prefix+"timeout-exchange";//死信交换机
       Queue queue= QueueBuilder
               .durable(name)
               .ttl(20*1000)//设置队列中消息最大存活时间，单位：ms
               .deadLetterExchange(timeoutExchange)//指定死信交换机
               .deadLetterRoutingKey(routingKey)
               .build();
       rabbitAdmin().declareQueue(queue);
       return queue;
   }

   @Bean
    public Queue timeoutQueue() { // 死信队列
       String queueName = prefix+"timeout-queue";
       Queue queue = new Queue(queueName, true);
       rabbitAdmin().declareQueue(queue);
       return queue;
   }

   @Bean
    public Binding bindingTtlQueue(@Qualifier("ttlQueue") Queue queue,
                                   @Qualifier("directExchangeTTL") DirectExchange exchange) {
       // 直连交换机需要指定 routingKey
       String routingKey = prefix+"routing-key";
       return BindingBuilder.bind(queue).to(exchange).with(routingKey);
   }

   @Bean
    public Binding bindTimeoutQueue(@Qualifier("timeoutQueue") Queue queue,
                                    @Qualifier("directExchangeTimeout") DirectExchange exchange) {
       String routingKey = prefix+"order";
       return BindingBuilder.bind(queue).to(exchange).with(routingKey);
   }
}
