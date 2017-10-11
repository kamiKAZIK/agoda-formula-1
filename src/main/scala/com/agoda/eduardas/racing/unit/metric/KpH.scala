package com.agoda.eduardas.racing.unit.metric

final case class KpH(private val underlying: Float) extends AnyVal {
  def toMs: Ms = Ms(underlying * 10/36)
}
