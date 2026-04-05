package org.example.service;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.example.entity.OrderMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.entity.OrderMessage;

/**
 * 类说明：订单Service类
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/4/5 11:50
 */
@Service
public class OrderService {
    // 注入一个Rocket MQ的模板类依赖
    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    // 编写一个创建订单的方法
    public void create(){
        OrderMessage orderMessage = new OrderMessage();
        orderMessage.setId(10L);
        orderMessage.setSn("12445678907");
        orderMessage.setPrice(109.6f);
        orderMessage.setPhone("177****7229");
        orderMessage.setUsername("吴文");
        // 将这个下单的消息投递到 rocket,异步发送
        rocketMQTemplate.asyncSend("order:create", orderMessage,new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("消息投递成功");
            }

            @Override
            public void onException(Throwable throwable) {
                System.out.println("消息投递失败");
            }
        });
    }
}
