package producer;

import lombok.SneakyThrows;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * 类说明：单向随机消息
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/3/29 16:29
 */
public class OnewayRandomTest {
    @Test
    @SneakyThrows
    public void test() {
        // 创建一个消息队列的消息生产者对象，指定分组，若分组不存在则自动创建
        DefaultMQProducer producer = new DefaultMQProducer("test-prod-g1");
        // 指定Rocket NamesServer的地址
        producer.setNamesrvAddr("192.168.227.128:9876");
        // 单项消息不支持重试
//        producer.setRetryTimesWhenSendAsyncFailed(3);
        producer.start();// 启动生产者
        String topic = "Music";
        String tag="QwQ";
        byte[] content = "我是一个单向随机消息".getBytes(StandardCharsets.UTF_8);
        Message message = new Message(topic, tag, content);
        // 发送单向消息
        producer.sendOneway(message);
        System.out.println("发送完毕");
        producer.shutdown();
    }
}
