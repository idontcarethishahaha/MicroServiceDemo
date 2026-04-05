import lombok.SneakyThrows;
import org.example.RocketMQProducerApp;
import org.example.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * 类说明：订单业务测试类
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/4/5 12:57
 */
// 启动类的类名
@SpringBootTest(classes= RocketMQProducerApp.class)
@RunWith(SpringRunner.class)
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @Test
    @SneakyThrows
    public void test() {
        orderService.create();
        System.out.println("订单已生成（假的）");//为了给客户端快速地返回一个结果
        TimeUnit.SECONDS.sleep(5);
    }

}
