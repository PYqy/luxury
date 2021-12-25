package base.consumer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * 发送同步消息
 */
public class SyncProducer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        //创建对象
        DefaultMQProducer producer = new DefaultMQProducer();
        producer.setProducerGroup("rocketMQ_test1");

        //指定nameserver
        producer.setNamesrvAddr("192.168.0.149:9876");
        //  启动peoduce
        producer.start();
        // 创建消息对象
        Message message = new Message();
        message.setTopic("base");
        message.setTags("tag1");
        message.setBody("Hello rocketMq".getBytes(StandardCharsets.UTF_8));
        //发送消息
        SendResult result = producer.send(message);
        System.out.println(result.getSendStatus());
        System.out.println(result.getMsgId());
        System.out.println(result.getRegionId());
        System.out.println(result.getMessageQueue().getQueueId());

        TimeUnit.SECONDS.sleep(1);
        // close producer
        producer.shutdown();

    }
}
