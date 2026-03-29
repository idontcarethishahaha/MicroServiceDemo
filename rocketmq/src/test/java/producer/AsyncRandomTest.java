package producer;

import lombok.SneakyThrows;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * 类说明：异步随机消息
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/3/29 16:18
 */
public class AsyncRandomTest {
    @Test
    @SneakyThrows
    public void test() {
        // 创建一个消息队列的消息生产者对象，指定分组，若分组不存在则自动创建
        DefaultMQProducer producer = new DefaultMQProducer("test-prod-g1");
        // 指定Rocket NamesServer的地址
        producer.setNamesrvAddr("192.168.227.128:9876");
        // 修改投递失败的重试次数，默认2次
        producer.setRetryTimesWhenSendAsyncFailed(3);
        producer.start();// 启动生产者
        String topic = "Music";// 一级分类
        String tag="QwQ";
        byte[] content = "我是一个异步随机消息".getBytes(StandardCharsets.UTF_8);
        Message message = new Message(topic, tag, content);

        producer.send(message,new SendCallback() {
            // ctrl+i
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("投递成功");
            }

            @Override
            public void onException(Throwable throwable) {
                System.out.println("投递过程失败，可以将消息存入数据库或文件以待后续查看");
            }
        });
        System.out.println("异步消息发送之后，不会等待队列的返回结果，而是立刻向下执行其他操作！");
        TimeUnit.SECONDS.sleep(5);//睡眠5秒，观察控制台输出
        producer.shutdown();//关闭生产者实例
    }
}
