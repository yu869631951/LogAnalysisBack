package com.atlanticyu.loganalysisback.scala.sparkstreaming
import java.util.Properties

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.{SparkConf, SparkContext}
import org.springframework.stereotype.Component
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}


@Component
class OperateFileDirectory {
  def start(): Unit = {
    println("Hello, world! start") // 输出 Hello World
  }
  def AnalysisIP(): Unit = {
    val spark = SparkSession.builder().appName("MysqlInsertDemo").master("local").getOrCreate()
    val url = "jdbc:mysql://localhost:3306/log_analysis_db?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC"
    val prop = new Properties()
    prop.put("user", "root")
    prop.put("password", "123456")
    //读取csv格式文件形成DataFrame ，并且注册成临时表
    val path = "hdfs://192.168.248.128:9000/net_log.csv"
    var schema = StructType(Array(
      StructField("id",IntegerType),
      StructField("xh",StringType),
      StructField("class",StringType),
      StructField("time",IntegerType),
      StructField("ip",StringType),
      StructField("host",StringType),
      StructField("app_name",StringType),
      StructField("desc",StringType)
    ))

    val df: DataFrame = spark.read
      .format("com.databricks.spark.csv") // 读取指定格式的文件 需要指定具体的包名称
      .option("header","false") // 读取csv文件的时候，头部文件是否当做列名
      .schema(schema)
      .load(path)

    // 注册临时表
    df.createOrReplaceTempView("tmp_Analysis")

    //数据处理
    spark.sql(
      """
        | select
        | ip as ip_value,
        | count(*) as visit_count
        | from tmp_Analysis
        | group by ip
      """.stripMargin)
      .createOrReplaceTempView("ip_visit_count")
    //spark.read.table("tmp02").show()
    //操作表 ,按照年龄进行降序排列
    val resultDFIpVisitCount: DataFrame = spark.sql("select * from ip_visit_count order by visit_count desc")
    resultDFIpVisitCount.write.mode(SaveMode.Overwrite).jdbc(url,"ip_visit_count",prop)
  }
  def AnalysisUrl(): Unit = {
    val spark = SparkSession.builder().appName("MysqlInsertDemo").master("local").getOrCreate()
    val url = "jdbc:mysql://localhost:3306/log_analysis_db?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC"
    val prop = new Properties()
    prop.put("user", "root")
    prop.put("password", "123456")
    //读取csv格式文件形成DataFrame ，并且注册成临时表
    val path = "hdfs://192.168.248.128:9000/net_log.csv"
    var schema = StructType(Array(
      StructField("id",IntegerType),
      StructField("xh",StringType),
      StructField("class",StringType),
      StructField("time",IntegerType),
      StructField("ip",StringType),
      StructField("host",StringType),
      StructField("app_name",StringType),
      StructField("desc",StringType)
    ))

    val df: DataFrame = spark.read
      .format("com.databricks.spark.csv") // 读取指定格式的文件 需要指定具体的包名称
      .option("header","false") // 读取csv文件的时候，头部文件是否当做列名
      .schema(schema)
      .load(path)

    // 注册临时表
    df.createOrReplaceTempView("tmp_Analysis")

    //数据处理
    spark.sql(
      """
        | select
        | host as url_value,
        | count(*) as visit_count
        | from tmp_Analysis
        | group by host
      """.stripMargin)
      .createOrReplaceTempView("url_visit_count")
    //spark.read.table("tmp02").show()
    //操作表 ,按照年龄进行降序排列
    val resultDFIpVisitCount: DataFrame = spark.sql("select * from url_visit_count order by visit_count desc")
    resultDFIpVisitCount.write.mode(SaveMode.Overwrite).jdbc(url,"url_visit_count",prop)
  }
  //todo：时间戳需要前端处理
  def AnalysisNetTime(): Unit = {
    val spark = SparkSession.builder().appName("MysqlInsertDemo").master("local").getOrCreate()
    val url = "jdbc:mysql://localhost:3306/log_analysis_db?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC"
    val prop = new Properties()
    prop.put("user", "root")
    prop.put("password", "123456")
    //读取csv格式文件形成DataFrame ，并且注册成临时表
    val path = "hdfs://192.168.248.128:9000/net_log.csv"
    var schema = StructType(Array(
      StructField("id",IntegerType),
      StructField("xh",StringType),
      StructField("class",StringType),
      StructField("time",IntegerType),
      StructField("ip",StringType),
      StructField("host",StringType),
      StructField("app_name",StringType),
      StructField("desc",StringType)
    ))

    val df: DataFrame = spark.read
      .format("com.databricks.spark.csv") // 读取指定格式的文件 需要指定具体的包名称
      .option("header","false") // 读取csv文件的时候，头部文件是否当做列名
      .schema(schema)
      .load(path)

    // 注册临时表
    df.createOrReplaceTempView("tmp_Analysis")

    //数据处理
    spark.sql(
      """
        | select
        | time as time_value,
        | count(*) as visit_count
        | from tmp_Analysis
        | group by time
      """.stripMargin)
      .createOrReplaceTempView("net_time")
    //spark.read.table("tmp02").show()
    //操作表 ,按照年龄进行降序排列
    val resultDFIpVisitCount: DataFrame = spark.sql("select * from net_time order by visit_count desc")
    resultDFIpVisitCount.write.mode(SaveMode.Overwrite).jdbc(url,"net_time",prop)
  }
}
object OperateFileDirectory {
  def main(args: Array[String]): Unit = {
//    val conf = new SparkConf().setMaster("master").setAppName("appName1")
//    val sc = new SparkContext(conf)
    println("Hello, world! main") // 输出 Hello World
  }
}
