package com.cw.kafka.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @author david.cai
 * @date 2018/5/22
 **/
@Slf4j
public class ProducerInterceptorDemo implements ProducerInterceptor<String, String> {
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        int key = Integer.parseInt(record.key());
        //if (key % 2 == 0){
            log.debug("================================onSend:{}", record.toString());
        //}
        return record;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        //if (metadata != null && "second".equals(metadata.topic()) && metadata.partition() == 0){
            log.debug("*************************onAcknowledgement:{}", metadata);
        //}
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
