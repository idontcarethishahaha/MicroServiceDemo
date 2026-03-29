package producer;

import lombok.SneakyThrows;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
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
public class SyncOrderTest {
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
        byte[] content = "我是一个同步顺序消息".getBytes(StandardCharsets.UTF_8);
        Message m0 = new Message(topic, tag, content);
        MessageQueue mq = new MessageQueue(topic,"broker-a",2);
        Message m1 = new Message(topic,tag,"周杰伦上台了".getBytes(StandardCharsets.UTF_8));
        Message m2 = new Message(topic,tag,"周杰伦演唱了《青花瓷》".getBytes(StandardCharsets.UTF_8));
        Message m3 = new Message(topic,tag,"周杰伦下台了".getBytes(StandardCharsets.UTF_8));
        // 为了保证消息时有序的，把他们投递到同一个队列中
        SendResult sr0 = producer.send(m0,mq,5000);
        SendResult sr1 = producer.send(m1,mq,5000);
        SendResult sr2 = producer.send(m2,mq,5000);
        SendResult sr3 = producer.send(m3,mq,5000);
        System.out.println("发送成功");

        producer.shutdown();
    }
}
