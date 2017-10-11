package com.agoda.eduardas.racing.unit.metric

final case class Meter(private val underlying: Float) extends AnyVal {
  def +(f: Meter): Meter = Meter(underlying + f.underlying)
  def -(f: Meter): Meter = Meter(underlying - f.underlying)
  def abs: Meter = Meter(Math.abs(underlying))
  def <=(f: Meter): Boolean = underlying <= f.underlying
  def <(f: Meter): Boolean = underlying < f.underlying
}
