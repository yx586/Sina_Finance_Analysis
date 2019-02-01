package com.java.kafka;//import util.properties packages

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class SimpleProducer {

    public static void main(String[] args) throws Exception {

        //Assign topicName to string variable
        sendMessage("Hello World!");

    }

    public static void sendMessage(String value) throws Exception {
        String topic = "test01";

        // create instance for properties to access producer configs
        Properties props = new Properties();
        //Assign localhost id
        props.put("bootstrap.servers", "stg.biapp01.hairongyi.local:9092,stg.biapp02.hairongyi.local:9092,stg.biapp03.hairongyi.local:9092");
        //Set acknowledgements for producer requests.
        props.put("acks", "all");
        //If the request fails, the producer can automatically retry,
        props.put("retries", 0);
        props.put("max.in.flight.requests.per.connection", 1);
        //Specify buffer size in config
        props.put("batch.size", 16384);
        //Reduce the no of requests less than 0
        props.put("linger.ms", 1);
        //The buffer.memory controls the total amount of memory available to the producer for buffering.
        props.put("buffer.memory", 33554432);
        props.put("max.request.size", "200000000");

        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");


//        Producer<String, String> producer = new KafkaProducer(props);
        Producer<String, String> producer = new KafkaProducer<String, String>(props);

        String key = "key";

        for (int i = 0; i < 1; i++) {

            producer.send(new ProducerRecord<String, String>(topic, key,value));

        }

        producer.close();
    }
}