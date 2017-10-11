package com.agoda.eduardas.racing

import com.agoda.eduardas.racing.unit.metric.{KpH, Ms}
import org.scalatest.{FlatSpec, Matchers}

final class VehicleSpec extends FlatSpec with Matchers {
  "Vehicle" should "return it's values according to team" in {
    val vehicle = Vehicle(2)

    vehicle.topSpeed shouldBe KpH(150 + 10 * 2).toMs
    vehicle.acceleration shouldBe Ms(2 * 2)
  }
}
