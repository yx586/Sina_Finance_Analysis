package kafka

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent

class Consumer (bootServer:String, groupId:String, ssc:StreamingContext, maxFetch:Int, maxPoll:Int, maxRecords:Int) {

  private val kafkaParamsWithCompress = Map[String, Object](
    "bootstrap.servers" -> bootServer,
    "key.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer",
    "value.deserializer" -> "org.apache.kafka.common.serialization.ByteArrayDeserializer",
    "auto.offset.reset" -> "latest",
    "fetch.max.bytes" -> (1024 * 1024 * maxFetch).toString,
    "group.id" -> groupId,
    "enable.auto.commit" -> "false",
    "max.poll.records" -> maxRecords.toString,
    "max.poll.interval.ms" -> (1000L * maxPoll).toString

  )

  private val kafkaParams = Map[String, Object](
    "bootstrap.servers" -> bootServer,
    "key.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer",
    "value.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer",
    "auto.offset.reset" -> "latest",
    "fetch.max.bytes" -> (1024 * 1024 * maxFetch).toString,
    "group.id" -> groupId,
    "enable.auto.commit" -> "false",
    "max.poll.records" -> maxRecords.toString,
    "max.poll.interval.ms" -> (1000L * maxPoll).toString
  )

  private val sContext = ssc

  //消费压缩数据
  def createDirectCompressStream(topics:Array[String]): InputDStream[ConsumerRecord[String,Array[Byte]]] = {
    KafkaUtils.createDirectStream[String, Array[Byte]](sContext, PreferConsistent, Subscribe[String, Array[Byte]](topics, kafkaParamsWithCompress))
  }

  //消费非压缩数据
  def createDirectStream(topics:Array[String]): InputDStream[ConsumerRecord[String,String]] = {
    KafkaUtils.createDirectStream[String, String](sContext, PreferConsistent, Subscribe[String, String](topics, kafkaParams))
  }

}
