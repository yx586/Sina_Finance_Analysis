package spark

import com.java.utils.DateUtil
import com.scala.caseclass.{finance, finance_analysis}
import kafka.Consumer
import org.apache.spark.SparkConf
import org.apache.spark.sql.{Encoders, SparkSession}
import org.apache.spark.sql.functions.from_json
import org.apache.spark.streaming.kafka010.{CanCommitOffsets, HasOffsetRanges}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object Spark_Streaming {

  def main (args: Array[String]): Unit = {
    val Array(topic,groupId,bootServer) = args

    val BOOT_SERVER = bootServer
    val TOPIC = topic
    val GID = groupId
    val APP_NAME = "Spark Streaming Finance Analysis"

    val sparkConf = new SparkConf()
      .setMaster("local[4]")
      .setAppName(APP_NAME)
      .set("spark.driver.allowMultipleContexts", "true")

    val ssc = new StreamingContext(sparkConf, Seconds(5))
    ssc.sparkContext.setLogLevel("WARN")

    val spark = SparkSession.builder.config(ssc.sparkContext.getConf).getOrCreate()
    spark.sql("set spark.sql.shuffle.partitions = 6");

    import spark.implicits._
    //数据Schema
    val finance_schema = Encoders.product[finance_analysis].schema
    val finance_analy_schema = Encoders.product[finance].schema

    //消费者
    val consumer = new Consumer(BOOT_SERVER,GID,ssc,10,30,50)
    val stream = consumer.createDirectStream(Array(TOPIC))

    //stream处理
    stream.foreachRDD{
      rdd =>{

        println("------------------------------------------------")
        var start = System.currentTimeMillis();
        println(DateUtil.getDateStr(start))
        //set offset
        val offsetRanges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges

        val ds = rdd.map(record => (record.key(),record.value())).toDS()
        if(ds.count() > 0){

          val finance_analy_ds = ds.withColumn("finance",from_json($"value",finance_analy_schema)).selectExpr("finance.data")
          val data_ds = finance_analy_ds.selectExpr("explode(data) finance").selectExpr("finance.*")

          data_ds.show(10,false)

        }else{
          ds.unpersist()
        }

        val end = System.currentTimeMillis();
        println("end past:[" + (end - start) + "ms]")
        //
        stream.asInstanceOf[CanCommitOffsets].commitAsync(offsetRanges)

      }
    }

  }

}
