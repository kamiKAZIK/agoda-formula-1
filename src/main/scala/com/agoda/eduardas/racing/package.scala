package com.agoda.eduardas

import com.agoda.eduardas.racing.unit.metric.{KpH, Ms}

import scala.language.implicitConversions

package object racing {
  implicit class Team(private val number: Int) extends AnyVal {
    def speed: Ms = KpH(150 + 10 * number).toMs
    def acceleration: Ms = Ms(2 * number)
    def asTeam: Team = Team(number)

    override def toString: String = s"Team[$number]"
  }
}
