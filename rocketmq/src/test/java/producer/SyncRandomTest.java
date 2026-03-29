package producer;

import lombok.SneakyThrows;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * 类说明：生产同步随机消息
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/3/29 16:06
 */
public class SyncRandomTest {
    @Test
    @SneakyThrows
    public void test() {
        DefaultMQProducer producer = new DefaultMQProducer("test-prod-g1");
        // 指定Rocket NamesServer的地址
        producer.setNamesrvAddr("192.168.227.128:9876");
        producer.start();// 启动生产者
        String topic = "Music";// 一级分类
         String tag="QwQ";
        byte[] content = "我是一个同步随机消息".getBytes(StandardCharsets.UTF_8);
        Message message = new Message(topic, tag, content);
        try {
            SendResult sendResult = producer.send(message,5000);
            System.out.println(sendResult.getMsgId()+":"+sendResult.getSendStatus());
        }catch (Exception e) {
            System.out.println("投递失败，重新投递");
            SendResult sendResult = producer.send(message,5000);
            System.out.println(sendResult.getMsgId()+":"+sendResult.getSendStatus());
        }finally {
            producer.shutdown();//关闭生产者
        }
    }
}
