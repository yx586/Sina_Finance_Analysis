package spark

import java.util.Properties

import com.scala.caseclass.finance
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer09
import org.apache.flink.table.api.TableEnvironment
import org.apache.flink.table.descriptors.{FileSystem, Json, Kafka, Schema}
import org.apache.spark.sql.Encoders
import org.apache.flink.streaming.api.scala._

object Flink_Streaming {

  private val ZOOKEEPER_HOST = "node1:2181,node2:2181,node3:2181"
  private val KAFKA_BROKER = "node1:9092,node2:9092,node3:9092"
  private val GROUPID = "transaction"


  def main (args: Array[String]): Unit = {

    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val tableEnv = TableEnvironment.getTableEnvironment(env)

    val finance_analy_schema = new Schema()
      .field("symbol", "String")
      .field("chg", "String")
      .field("mktcap", "String")
      .field("cname", "String")
      .field("diff", "String")
      .field("volume", "String")
      .field("market", "String")
      .field("high", "String")
      .field("amplitude", "String")
      .field("category_id", "String")
      .field("low", "String")
      .field("pe", "String")
      .field("price", "String")
      .field("name", "String")
      .field("category", "String")
      .field("preclose", "String")
      .field("open", "String")


//    val source = tableEnv.connect(new Kafka()
//            .version("0.11")
//            .topic("test01")
//            .startFromEarliest()
//            .property("zookeeper.connect", ZOOKEEPER_HOST)
//            .property("bootstrap.servers", KAFKA_BROKER)
//            .property("group.id", GROUPID)
//            .startFromLatest()
//            .sinkPartitionerFixed()
//    )

//    val jsonfile = tableEnv.connect(new FileSystem().path("D:\\tmp\\analysis\\201901301.json"))
//
//    val a = jsonfile.withFormat(new Json().jsonSchema(finance_analy_schema.toString))


  }

}
