package com.agoda.eduardas.racing.unit.metric

final case class Ms(private val underlying: Float) extends AnyVal {
  def toKpH: KpH = KpH(underlying * 36 / 10)
  def +(f: Ms): Ms = Ms(underlying + f.underlying)
  def -(f: Ms): Ms = Ms(underlying - f.underlying)
  def *(f: Ms): Ms = Ms(underlying * f.underlying)
  def *(f: Int): Ms = Ms(underlying * f)
  def min(f: Ms): Ms = Ms(Math.min(underlying, f.underlying))
  def asDistance: Meter = Meter(underlying)
  def <(f: Ms): Boolean = underlying < f.underlying
  def >(f: Ms): Boolean = underlying > f.underlying
}

