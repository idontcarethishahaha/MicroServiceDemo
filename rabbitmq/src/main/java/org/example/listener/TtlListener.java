package org.example.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 类说明：监听死信队列
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/4/12 11:34
 */
@Slf4j
@Component
@RabbitListener(queuesToDeclare = @Queue("admin.ttl.timeout-queue"))
public class TtlListener {
    @RabbitHandler
    public void handle(String msg){
        log.warn("监听到一个延迟消息，内容：{}",msg);
    }
}
