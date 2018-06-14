package com.cw.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Arrays;
import java.util.Properties;

/**
 * @author david.cai
 * @date 2018/6/4
 **/
@Slf4j
public class ConsumerExample {

    private static Properties props = new Properties();

    public static void main(String[] args) {
        initProperties();

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);

        consumer.subscribe(Arrays.asList("second"));

        try {
            log.info("开始consumer");
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(100);

                records.forEach(r -> {
                    log.info("**********partition {}, key {}, value {}, offset {}", r.partition() ,r.key(), r.value(), r.offset());
                });
            }
        } finally {
            log.info("关闭consumer");
            consumer.close();
        }
    }

    public static void initProperties(){
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.199.197.224:9092,10.199.197.234:9092 ");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "second-test");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
    }
}
