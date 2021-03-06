package com.scala.spark

import com.java.utils.DateUtil
import com.scala.caseclass.{finance, finance_analysis}
import org.apache.spark.sql.{Encoders, SQLContext, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.functions._

object Spark_Offline {
  def main (args: Array[String]): Unit = {
    //spark基础配置
    val conf = new SparkConf().setAppName("Spark SQL basic example").setMaster("local[4]")
    val sc = new SparkContext(conf)
    val spark = SparkSession.builder().config(sc.getConf).getOrCreate()
    val sqlcontext = new SQLContext(sc)

    //隐式转换
    import spark.implicits._

    //数据schema
    val finance_schema = Encoders.product[finance_analysis].schema
    val finance_analy_schema = Encoders.product[finance].schema

    val path = "D:\\tmp\\analysis\\"
    val ds = spark.read.textFile(path)

    //单个json处理
    //    val dataframe = sqlcontext.read.format("json").load("D:\\tmp\\analysis\\201901301.json")
    //    dataframe.printSchema();
    //    val data = dataframe.selectExpr("explode(data) finance").selectExpr("finance.*")
    //    val ds = data.as[finance_analysis]
    //    ds.show(10,false)
    //    ds.printSchema()

    //批量json处理
    val finance_analy_ds = ds.withColumn("finance",from_json($"value",finance_analy_schema)).selectExpr("finance.data")
    val data_ds = finance_analy_ds.selectExpr("explode(data) finance").selectExpr("finance.*","current_timestamp as time")
    data_ds.printSchema()
    val finance_analysis_ds = data_ds.as[finance_analysis]
    finance_analysis_ds.show(10,false)
//    data_ds.repartition(1).write.format("parquet").save("D:\\tmp\\analysis\\result.parquet")

  }
}
