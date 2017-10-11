package com.agoda.eduardas.racing

import com.agoda.eduardas.racing.unit.metric.Meter

object RacingApplication extends App {
  val raceTrack = new RaceTrack(5, Meter(100000))
  raceTrack.race()
  println("===SCORE===")
  println(raceTrack.score)
}
