package com.cw.kafka;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author david.cai
 * @date 2018/4/25
 **/
@Slf4j
public class ProducerExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.199.197.224:9092,10.199.197.234:9092 ");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        int i = 0;
        while(i < Integer.MAX_VALUE) {
            ProducerRecord<String, String> producerRecord = new ProducerRecord("first", i + "", "hello" + i);

            Future<RecordMetadata> f = producer.send(producerRecord);
            RecordMetadata recordMetadata = f.get();
            log.debug("partition:{}, offset:{}", recordMetadata.partition(), recordMetadata.offset());

            i ++;
        }
        producer.close();
    }


}
