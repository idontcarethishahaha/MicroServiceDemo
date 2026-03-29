package producer;

import lombok.SneakyThrows;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * 类说明：同步顺序消息
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/3/29 16:36
 */
public class OnewayOrderTest {
    @Test
    @SneakyThrows
    public void test(){
        DefaultMQProducer producer = new DefaultMQProducer("test-prod-g1");
        // 指定Rocket NamesServer的地址
        producer.setNamesrvAddr("192.168.227.128:9876");
        // 修改投递失败的重试次数，默认2次
        producer.setRetryTimesWhenSendAsyncFailed(3);
        producer.start();// 启动生产者
        String topic = "Music";// 一级分类
        String tag="QwQ";
        byte[] content = "我是一个单向顺序消息".getBytes(StandardCharsets.UTF_8);
        Message message = new Message(topic, tag, content);
        // 调用sendOneway方法，同时指定消息队列就可以实现单向的顺序消息
        MessageQueue messageQueue = new MessageQueue(topic,"broker-a",0);
        producer.sendOneway(message,messageQueue);

        producer.shutdown();
    }
}
