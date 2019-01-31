package com.scala.spark

import com.scala.caseclass.{finance, finance_analysis}
import org.apache.spark.sql.{Encoders, SQLContext, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.functions._

object Spark_Test {
  def main (args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("Spark SQL basic example").setMaster("local[4]")
    val sc = new SparkContext(conf)
    val spark = SparkSession.builder().config(sc.getConf).getOrCreate()
    val sqlcontext = new SQLContext(sc)

    import spark.implicits._

    val finance_schema = Encoders.product[finance_analysis].schema
    val finance_analy_schema = Encoders.product[finance].schema
    val path = "D:\\tmp\\analysis\\"

    val ds = spark.read.textFile(path)


    val ds1 = ds.withColumn("finance",from_json($"value",finance_analy_schema)).selectExpr("finance.data")
    val ds2 = ds1.selectExpr("explode(data) finance").selectExpr("finance.*")
    ds2.printSchema()
    ds2.show(10,false)



//    val dataframe = sqlcontext.read.format("json").load("D:\\tmp\\analysis\\201901301.json")
//    dataframe.printSchema();
//    val data = dataframe.selectExpr("explode(data) finance").selectExpr("finance.*")
//    data.printSchema()
//    data.show(10,false)
//    val ds = data.as[finance_analysis]
//    ds.show(10,false)
//    ds.printSchema()
  }
}
