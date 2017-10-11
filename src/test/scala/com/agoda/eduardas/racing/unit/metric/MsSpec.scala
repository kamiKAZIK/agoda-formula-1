package com.agoda.eduardas.racing.unit.metric

import org.scalatest.{FlatSpec, Matchers}

final class MsSpec extends FlatSpec with Matchers {
  "Ms" should "convert to KpH correctly" in {
    Ms(10).toKpH shouldBe KpH(10.0f * 36 / 10)
  }

  "Ms" should "add another Ms correctly" in {
    Ms(1) + Ms(1) shouldBe Ms(2)
  }

  "Ms" should "be multiplied by another Ms correctly" in {
    Ms(2) * Ms(2) shouldBe Ms(4)
  }

  "Ms" should "be multiplied by Int correctly" in {
    Ms(2) * 2 shouldBe Ms(4)
  }

  "Ms" should "return valid minimum value" in {
    Ms(2).min(Ms(1)) shouldBe Ms(1)
  }

  "Ms" should "convert to Meter correctly" in {
    Ms(2).asDistance shouldBe Meter(2)
  }

  "Ms" should "do LT comparison correctly" in {
    Ms(1) < Ms(2) shouldBe true
    Ms(2) < Ms(2) shouldBe false
    Ms(3) < Ms(2) shouldBe false
  }

  "Ms" should "do GT comparison correctly" in {
    Ms(1) > Ms(2) shouldBe false
    Ms(2) > Ms(2) shouldBe false
    Ms(3) > Ms(2) shouldBe true
  }
}
