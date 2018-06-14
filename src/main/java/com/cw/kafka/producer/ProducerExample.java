package com.cw.kafka.producer;


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

    private static Properties props = new Properties();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        initProperties();

        Producer<String, String> producer = new KafkaProducer<>(props);
        int i = 0;
        while(i < Integer.MAX_VALUE) {
            ProducerRecord<String, String> producerRecord = new ProducerRecord("second", i + "", "hello" + i);
//            ProducerRecord<String, String> producerRecord = new ProducerRecord("second","hello" + i);

            Future<RecordMetadata> f = producer.send(producerRecord);
            RecordMetadata recordMetadata = f.get();
            log.debug("partition:{}, offset:{}", recordMetadata.partition(), recordMetadata.offset());

            i ++;
        }
        producer.close();
    }

    public static void initProperties(){
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.199.197.224:9092,10.199.197.234:9092 ");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, "com.cw.kafka.interceptor.ProducerInterceptorDemo");
    }
}
