package com.scala.spark

import org.apache.kafka.common.TopicPartition
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.{Seconds, StreamingContext}

object OffsetApp {

  def main (args: Array[String]): Unit = {

    val sc = new SparkConf().setMaster( "local[2]" ).setAppName( "OffsetApp" )
    val ssc = new StreamingContext( sc, Seconds( 10 ) )

    val kafkaParams = Map(
      "" -> ""
    )
    val topics = Array( "test01" )

    val stream =
      KafkaUtils.createDirectStream[String, String]( ssc, PreferConsistent, Subscribe[String, String]( topics, kafkaParams ) )

    val logRdd = stream.map(e => {
      new String(e.value().toString)
    })

    val datas = logRdd.map(line => {
      val index: Array[String] = line.split(",")
      val ip = index(0)
      (ip,1)
    }
    )

    datas.print()

    datas.foreachRDD(cs => {
      cs.foreachPartition(f => {
        f.foreach(s => {

        })
      })
    })

    ssc.start()
    ssc.awaitTermination()

  }

}
