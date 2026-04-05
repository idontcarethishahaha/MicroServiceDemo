package org.example.listener;

import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.example.entity.OrderMessage;
import org.springframework.stereotype.Component;

/**
 * 类说明：订单消息监听器
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/4/5 13:09
 */
@Component
@RocketMQMessageListener(consumerGroup = "test-consumer-group",
                         topic="order",
                         selectorExpression = "create",
                         consumeMode = ConsumeMode.CONCURRENTLY,
                         messageModel = MessageModel.CLUSTERING)
public class OrderMessageListener implements RocketMQListener<OrderMessage> {
    @Override
    public void onMessage(OrderMessage msg) {
        // 当监听器收到新消息后会自动调用本方法
        String msgTemplate = String.format(
                "您好，[%s]!您的订单(%s)已被处理，金额为：%.1f",
                msg.getUsername(),
                msg.getSn(),
                msg.getPrice()
        );
        System.out.println("模拟在数据库中创建订单~");
        System.out.println("模拟向用户发送短信："+msgTemplate);
    }
}
