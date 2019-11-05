package cc.eslink.producer.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 *@ClassName Producer
 *@Description TODO
 *@Author zeng.yakun (0178)
 *@Date 2019/11/5 17:18
 *@Version 1.0
 **/
public class Producer {

    public static String topic = "eslink_test";

    public static void main(String[] args) {
        Properties p = new Properties();
        p.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.2.150:9092");
        p.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        p.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(p);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm:ss:SSS");
            while (true) {
                String msg = "Hello," + new Random().nextInt(100) + "," + formatter.format(LocalDateTime.now());
                ProducerRecord<String, String> record = new ProducerRecord<>(topic, msg);
                Future<RecordMetadata> send = kafkaProducer.send(record);
                System.out.println("消息发送成功：" + msg);
//                Thread.sleep(500);
//                TimeUnit.SECONDS.sleep(1);
                TimeUnit.MILLISECONDS.sleep(500);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            kafkaProducer.close();
        }
    }
}
