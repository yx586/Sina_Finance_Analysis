package com.java.kafka;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class SimpleConsumer extends Thread{
    //消费者连接
    private final ConsumerConnector consumer;
    //要消费的话题
    private final String topic;

    public SimpleConsumer(String topic) {
        consumer =kafka.consumer.Consumer.createJavaConsumerConnector(createConsumerConfig());
        this.topic =topic;
    }

    //配置相关信息
    private static ConsumerConfig createConsumerConfig() {
        Properties props = new Properties();

        props.put("zookeeper.connect","stg.bihdp01.hairongyi.local:2181,stg.bihdp02.hairongyi.local:2181,stg.bihdp03.hairongyi.local:2181");
        props.put("group.id", "110");
        props.put("zookeeper.session.timeout.ms","10000");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");
       // props.put("auto.offset.reset", "earliest");


        return new ConsumerConfig(props);
    }

    public void run(){

        Map<String,Integer> topickMap = new HashMap<String, Integer>();
        topickMap.put(topic, 1);
        Map<String, List<KafkaStream<byte[],byte[]>>>  streamMap =consumer.createMessageStreams(topickMap);

        KafkaStream<byte[],byte[]> stream = streamMap.get(topic).get(0);
        ConsumerIterator<byte[],byte[]> it =stream.iterator();
        System.out.println("*********Results********");
        while(true){
            if(it.hasNext()){
                //打印得到的消息
                System.err.println(Thread.currentThread()+"接收到: " +new String(it.next().message()));
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        SimpleConsumer consumerThread = new SimpleConsumer("test01");
        consumerThread.start();
    }
}