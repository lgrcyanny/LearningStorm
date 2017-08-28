package com.learning.storm

import org.apache.storm.trident.{TridentState, TridentTopology}
import org.apache.storm.trident.operation.builtin.Count
import org.apache.storm.trident.testing.{FixedBatchSpout, MemoryMapState, Split}
import org.apache.storm.tuple.{Fields, Values}

/**
  * Created by lgrcyanny on 17/8/28.
  */
object TridentTest {

  def process() = {
    println("starting to define tridentstate")
    val topology = new TridentTopology()
    // define spout
    val spout = new FixedBatchSpout(new Fields("sentence"), 3,
      new Values("the cow jumped over the moon"),
      new Values("the man went to the store and bought some candy"),
      new Values("four score and seven years ago"),
      new Values("how many apples can you eat"))
    spout.setCycle(true)

    val wordsCount: TridentState = topology.newStream("wordsSpout", spout)
      .each(new Fields("sentence"), new Split(), new Fields("word"))
      .groupBy(new Fields("word"))
      .persistentAggregate(new MemoryMapState.Factory(), new Count(), new Fields("count"))
      .parallelismHint(6)
  }

  def main(args: Array[String]): Unit = {
    process()
  }

}
