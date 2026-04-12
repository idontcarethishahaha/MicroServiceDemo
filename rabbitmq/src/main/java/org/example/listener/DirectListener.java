package org.example.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 类说明：消费直连消息队列中的消息
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/4/12 9:44
 */
@Slf4j
@Component
// 当前监听器监听指定的某个（些）队列
@RabbitListener(queuesToDeclare = {@Queue("admin.direct.musicQueue"),
                                  @Queue("admin.direct.danceQueue")})
public class DirectListener {

    @RabbitHandler//用来声明一个监听器的方法
    public void consume(String msg) {
        log.info("成功消费了一条消息：{}",msg);
    }
}
