package com.java.kafka;


import kafka.producer.KeyedMessage;
import kafka.producer.Producer;
import kafka.producer.ProducerConfig;
import scala.collection.Seq;


import java.util.Properties;
import java.util.UUID;

public class KafkaProducer {

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put("serializer.class", "kafka.serializer");
        properties.put("metadata.broker.list", "10.26.13.19");
        properties.put("request.required.acks", "1");

        ProducerConfig producerConfig = new ProducerConfig(properties);
        Producer<String, String> producer = new Producer(producerConfig);

        String topic = "test01";

        for (int i = 0; i < 100; i++) {
            producer.send((Seq<KeyedMessage<String, String>>) new KeyedMessage<String,String>(topic,i+"", UUID.randomUUID()+""));
        }

    }

}
