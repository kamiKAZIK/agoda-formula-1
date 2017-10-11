package com.agoda.eduardas.racing

import com.agoda.eduardas.racing.unit.metric.{KpH, Ms}
import org.scalatest.{FlatSpec, Matchers}

final class TeamSpec extends FlatSpec with Matchers {
  "Team" should "convert from Int implicitly" in {
    val team: Team = 2
    team shouldBe Team(2)
    3.asTeam shouldBe Team(3)
  }

  "Team" should "return its values according to team number" in {
    val teamNumber = 3
    teamNumber.asTeam.speed shouldBe KpH(150 + 10 * teamNumber).toMs
    teamNumber.asTeam.acceleration shouldBe Ms(2 * teamNumber)
  }
}
