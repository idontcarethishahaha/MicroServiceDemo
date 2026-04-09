package org.example.config;

import jakarta.annotation.Resource;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 类说明：直连模式的配置类
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/4/9 19:28
 */
@Configuration
public class DirectConfig {
    @Resource
    private ConnectionFactory connectionFactory;
    private String prefix="admin.direct.";
    // RabbitAdmin 用于创建交换机和队列
    @Bean
    public RabbitAdmin rabbitAdmin() {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);// 设置其为自启动模式
        return rabbitAdmin;
    }

    @Bean
    public Queue directQueue() {
        String queueName=prefix+"musicQueue";//队列名称
        // 创建队列并指定类型，参数依次是：队列名称，消息是否做持久化，是否是独占的，无消费时是否自动删除
        Queue queue=new Queue(queueName, true, false, false);
        rabbitAdmin().declareQueue(queue);//声明当前队列
        return queue;
    }

    @Bean
    public Exchange directExchange() {
        String exchangeName=prefix+"musicExchange";
        DirectExchange exchange=new DirectExchange(exchangeName,true, false);
        rabbitAdmin().declareExchange(exchange);
        return exchange;
    }

    @Bean
    public Binding binding(@Qualifier("directQueue") Queue directQueue,
                           @Qualifier("directExchange") Exchange directExchange) {
        // 把当前队列和交换机绑在一起  路由键
        String routingKey=prefix+"music";//指定路由键
        // 使用声明好的路由键将上方的直连交换机和队列进行绑定
        return BindingBuilder.bind(directQueue).to(directExchange).with(routingKey).noargs();
    }
}
