package com.agoda.eduardas.racing.unit

import com.agoda.eduardas.racing.unit.metric.{Meter, Ms}
import com.agoda.eduardas.racing.{RaceTrack, Team}
import org.scalatest.{FlatSpec, Matchers}

final class RaceTrackSpec extends FlatSpec with Matchers {
  "RaceTrack" should "not allow to race again after finish" in {
    val raceTrack = new RaceTrack(3, Meter(100))
    raceTrack.race()
    val thrown = intercept[RuntimeException] {
      raceTrack.race()
    }
    thrown.getMessage shouldBe "This race has already finished!"
  }

  "RaceTrack" should "not allow to get score on pending race" in {
    val raceTrack = new RaceTrack(3, Meter(100))
    val thrown = intercept[RuntimeException] {
      raceTrack.score
    }
    thrown.getMessage shouldBe "Score is only available for finished races!"
  }

  "RaceTrack" should "provide valid score" in {
    val raceTrack = new RaceTrack(1, Meter(10))
    raceTrack.race()

    raceTrack.score.toList shouldBe List((Team(1), (Ms(12).toKpH, "0h 0min 4sec")))
  }
}
