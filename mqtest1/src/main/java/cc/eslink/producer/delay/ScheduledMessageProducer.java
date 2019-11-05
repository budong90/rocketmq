package cc.eslink.producer.delay;

import cc.eslink.Constant;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

/**
 *@ClassName ScheduledMessageProducer
 *@Description 延时消息
 *@Author zeng.yakun (0178)
 *@Date 2019/11/4 18:06
 *@Version 1.0
 **/
public class ScheduledMessageProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        producer.setNamesrvAddr(Constant.NAMESRV_ADDR);
        producer.start();
        for (int i = 0; i < 100; i++) {
            Message msg = new Message("TopicTest",
                    ("Hello scheduled mesage " + i).getBytes());
            msg.setDelayTimeLevel(3);
            producer.send(msg);
        }
        producer.shutdown();
    }
}
