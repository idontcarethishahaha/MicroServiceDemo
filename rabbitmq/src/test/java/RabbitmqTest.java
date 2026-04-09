import org.example.RabbitmqApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



/**
 * 类说明：测试RabbitMQ的各种消息
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/4/9 19:55
 */
@SpringBootTest(classes = RabbitmqApp.class)
@RunWith(SpringRunner.class)
public class RabbitmqTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private String prefix="admin.direct.";
    // 测试直连消息
    @Test
    public void testDirect() {
        String msg="周杰伦的七里香";
        String exchangeName=prefix+"musicExchange";
        String routingKey=prefix+"music";
        // 向Rabbit发布一条消息，参数依次为：交换机的名称，路由键，消息内容
        rabbitTemplate.convertAndSend(exchangeName, routingKey, msg);
        System.out.println("消息投递成功");
    }

    @Test
    public void testFanout() {
        String msg="玛丽有只小羊羔~";
        //String exchangeName=prefix+"fanoutExchange";
        String exchangeName="admin.fanout.exchange";
        String routingKey="";//广播模式中，routingKey以空字符串形式即可
        rabbitTemplate.convertAndSend(exchangeName, routingKey, msg);
        System.out.println("广播消息发布成功！");
    }

    @Test
    public void testTopicA() {
        String msg="耶咦耶咦耶咦哦哦~刘德华有一笔新订单";
        String exchangeName="admin.topic.exchange";
        String routingKey="order.xxx";
        rabbitTemplate.convertAndSend(exchangeName, routingKey, msg);
        System.out.println("主题消息发布成功！");
    }

    @Test
    public void testTopicB() {
        String msg="孙佳欣卖出了100万个皮肤~";
        String exchangeName="admin.topic.exchange";
        String routingKey="sale.xxx";
        rabbitTemplate.convertAndSend(exchangeName, routingKey, msg);
        System.out.println("主题消息发布成功！");
    }
}
