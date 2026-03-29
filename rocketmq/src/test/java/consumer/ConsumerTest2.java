package consumer;

import lombok.SneakyThrows;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * 类说明：
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/3/29 15:58
 */
public class ConsumerTest2 {
    @SneakyThrows
    public static void main(String[] args) {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test-consumer-g3");
        consumer.setNamesrvAddr("192.168.227.128:9876");
        String topic = "Sport";
        String tag="QwQ";

        consumer.subscribe(topic,tag);

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                return null;
            }
        });


    }
}
