package com.agoda.eduardas.racing.unit.metric

import org.scalatest.{FlatSpec, Matchers}

final class MeterSpec extends FlatSpec with Matchers {
  "Meter" should "add another Meter correctly" in {
    Meter(1) + Meter(1) shouldBe Meter(2)
  }

  "Meter" should "subtract another Meter correctly" in {
    Meter(2) - Meter(1) shouldBe Meter(1)
  }

  "Meter" should "return absolute value correctly" in {
    (Meter(1) - Meter(2)).abs shouldBe Meter(1)
    (Meter(2) - Meter(1)).abs shouldBe Meter(1)
  }

  "Meter" should "do LTE comparison correctly" in {
    Meter(1) <= Meter(2) shouldBe true
    Meter(2) <= Meter(2) shouldBe true
    Meter(3) <= Meter(2) shouldBe false
  }

  "Meter" should "do LT comparison correctly" in {
    Meter(1) < Meter(2) shouldBe true
    Meter(2) < Meter(2) shouldBe false
    Meter(3) < Meter(2) shouldBe false
  }
}
