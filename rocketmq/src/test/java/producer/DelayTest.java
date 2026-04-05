package producer;

import lombok.SneakyThrows;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 类说明：测试延迟消息
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/4/5 10:45
 */
public class DelayTest {
    private static String nameSrv="192.168.227.128:9876";
    @Test
    @SneakyThrows
    public void test1() {
        DefaultMQProducer producer = new DefaultMQProducer("test-prod-g1");
        // 指定Rocket NamesServer的地址
        producer.setNamesrvAddr(nameSrv);
        producer.start();// 启动生产者
        Message message = new Message("Music",//主题
                "QwQ",//标签
                "我是一个延时消息".getBytes(StandardCharsets.UTF_8));//具体消息是啥（UTF-8避免中文乱码
        message.setDelayTimeLevel(3);//一共有18个等级，延迟等级3：10s
        //producer.send(message);
        SendResult sendResult = producer.send(message);//返回SendResult，同步消息，异步加一个回调对象
        System.out.println("消息投递成功");
        System.out.println(sendResult.getMsgId());
        System.out.println(sendResult.getSendStatus());
        for (int i = 0; i < 10; i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(i+1);//数秒
        }
    }

    // 编写一个消费者程序，消费上面的延时消息
    @SneakyThrows
    public static void main(String[] args) {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("mq-test-consumer");
        consumer.setNamesrvAddr(nameSrv);//设置name_server
        consumer.subscribe("Music","QwQ");//订阅指定主题和标签的队列
        // 注册一个消息监听器
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs,context)->{
            for(MessageExt msg : msgs){
                if(msg.getDelayTimeLevel()>0){
                    System.out.println("我收到了一条延时消息，延时等级："+msg.getDelayTimeLevel());
                }else{
                    System.out.println("我收到了一条普通消息");
                }
                System.out.println("消息内容："+new String(msg.getBody(),StandardCharsets.UTF_8));
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        System.out.println("消费者准备就绪~");
        consumer.start();//启动消费者
    }
}
