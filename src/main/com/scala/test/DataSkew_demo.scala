package com.scala.test

import scala.util.Random

import org.apache.spark.{SparkConf, SparkContext}

object DataSkew_demo {

  def main (args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("DataSkew_demo").setMaster("local[2]")
    val sc = new SparkContext(conf)

    val array = new Array[Int](10000000)
    for (i <- 0 to 9999){
      array(i) = new Random().nextInt(1000)
    }

    val rdd = sc.parallelize(array)

    val maprdd = rdd.map((_,1))

    val start1 = System.currentTimeMillis();
    maprdd.countByValue().map(i => (i._1._1,i._2)).foreach(print)
    val end1 = System.currentTimeMillis();
    println(end1 - start1)

    val start2 = System.currentTimeMillis();
    val prifixrdd = maprdd.map(x =>{
      val prifix = new Random().nextInt(10)
      (prifix+"_"+x._1,x._2)}
    )

    val tmprdd = prifixrdd.reduceByKey(_+_)

    val newrdd = tmprdd.map(x => (x._1.split("_")(1),x._2))
    newrdd.reduceByKey(_+_).foreach(print)
    val end2 = System.currentTimeMillis()
    println(end2 - start2)
  }

}
