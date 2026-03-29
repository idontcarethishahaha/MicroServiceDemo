package consumer;

import lombok.SneakyThrows;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.junit.Test;

import java.util.List;

/**
 * 类说明：测试消费消息
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/3/29 15:34
 */
public class ConsumerTest {
    //@Test
    @SneakyThrows
    public static void main(String[] args) {
        // 创建消费者实例
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test-consumer-g2");
        consumer.setNamesrvAddr("192.168.227.128:9876");
        String topic = "Music";
        String tag = "QwQ";
        // 订阅指定主题和标签的消息
        consumer.subscribe(topic, tag);
        // 设置监听器
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                // 循环遍历消息列表
                msgs.forEach(msg -> {
                   String content = new String(msg.getBody());//获取消息内容
                    System.out.println("ConsumerA成功消费到消息，内容如下："+content);
                });
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        // 启动消费者
        consumer.start();
        System.out.println("消费者启动完毕，等待消息ing...");
    }
}
