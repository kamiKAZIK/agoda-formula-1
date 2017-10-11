package com.agoda.eduardas.racing.unit.metric

import org.scalatest.{FlatSpec, Matchers}

final class KpHSpec extends FlatSpec with Matchers {
  "KpH" should "convert to Ms correctly" in {
    KpH(10).toMs shouldBe Ms(10.0f * 10/36)
  }
}
