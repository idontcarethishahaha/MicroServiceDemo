package producer;

import lombok.SneakyThrows;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * 类说明：测试生产消息
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/3/29 14:51
 */
public class ProducerTest {
    @Test
    @SneakyThrows
    public void test() {
        // 创建一个消息队列的消息生产者对象，指定分组，若分组不存在则自动创建
        DefaultMQProducer producer = new DefaultMQProducer("test-prod-g1");
        // 指定Rocket NamesServer的地址
        producer.setNamesrvAddr("192.168.227.128:9876");
        producer.start();// 启动生产者
        String[] topics = {"Sport","Weather","Music"};
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            // 随机得到一个主题
            String topic = topics[random.nextInt(topics.length)];
            String tag = "QwQ";// 指定这些消息的标签
            byte[] content = ("一条测试消息"+i).getBytes(StandardCharsets.UTF_8);
            // 创建一条消息，依次指定主题、标签和消息内容
            Message message = new Message(topic, tag, content);
            // 发送消息到broker，设置1秒的超时时间，返回结果，保存到sendResult中
            SendResult sendResult = producer.send(message,10*1000);
            System.out.printf("[%d]号消息的发送状态为：%s，其MsgId = %s\n",
                    i,
                    sendResult.getSendStatus(),
                    sendResult.getMsgId());
        }
    }
}
