package producer;

import lombok.SneakyThrows;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * 类说明：同步顺序消息
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/3/29 16:36
 */
public class ASyncOrderTest {
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
        byte[] content = "我是一个异步顺序消息".getBytes(StandardCharsets.UTF_8);
        Message message = new Message(topic, tag, content);
        MessageQueue messageQueue = new MessageQueue(topic,  "broker-a",0);
        String[] msgs = {"把冰箱门打开","把大象装进去","把冰箱门关上"};
        for(String msg : msgs){
            Message m = new Message(topic, tag, msg.getBytes(StandardCharsets.UTF_8));
            producer.send(m,messageQueue,new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("成功发送，内容如下："+msg);
                }

                @Override
                public void onException(Throwable throwable) {
                    System.out.println("失败");
                }
            });
            TimeUnit.SECONDS.sleep(2);
        }
        producer.shutdown();
    }
}
