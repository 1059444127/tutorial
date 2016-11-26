package com.cloudera.datascience

import org.apache.spark._
import org.apache.spark.streaming._

object SocketStream {
  def main(args: Array[String])  {
    val conf = new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount")
    val ssc = new StreamingContext(conf, Seconds(5))
    val lines = ssc.socketTextStream("localhost", 9999)
    val words = lines.flatMap(_.split(" "))
    val pairs = words.map(word => (word, 1))
    val wordCounts = pairs.reduceByKey(_ + _)

    // Print the first ten elements of each RDD generated in this DStream to the console
    wordCounts.print()
    println("......................................................................................................")

    ssc.start()             // Start the computation
    ssc.awaitTermination()
  }
}
