package com.agoda.eduardas.racing

case class Vehicle(private val team: Team) {
  val topSpeed = team.speed
  val acceleration = team.acceleration

  override def toString: String = s"[${topSpeed}, ${acceleration}]"
}
