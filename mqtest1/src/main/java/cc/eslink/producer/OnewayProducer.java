package cc.eslink.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 *@ClassName OnewayProducer
 *@Description 单向发送消息
 * 这种方式主要用在不特别关心发送结果的场景，例如日志发送。
 *@Author zeng.yakun (0178)
 *@Date 2019/11/4 12:27
 *@Version 1.0
 **/
public class OnewayProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        for (int i = 0; i < 100; i++) {
            Message msg = new Message("TopicTest",
                    "TagA",
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            producer.sendOneway(msg);
        }
        producer.shutdown();
    }
}
