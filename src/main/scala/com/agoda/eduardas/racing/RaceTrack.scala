package com.agoda.eduardas.racing

import com.agoda.eduardas.racing.unit.metric.{KpH, Meter, Ms}

import scala.collection.mutable
import scala.concurrent.duration.{Duration, _}

class RaceTrack(numberOfTeams: Int, length: Meter) {
  private val vehicles: Map[Team, Vehicle] = (1 to numberOfTeams).map(team => team.asTeam -> Vehicle(team)).toMap
  private val currentPosition: mutable.Map[Team, Meter] = mutable.Map((1 to numberOfTeams).map(team => team.asTeam -> Meter((team - 1) * -200.0f)) : _*)
  private val currentSpeed: mutable.Map[Team, Ms] = mutable.Map((1 to numberOfTeams).map(team => team.asTeam -> Ms(0)) : _*)
  private val nitroStatus: mutable.Map[Team, Boolean] = mutable.Map((1 to numberOfTeams).map(team => team.asTeam -> false) : _*)
  private val currentTimes: mutable.Map[Team, Duration] = mutable.Map((1 to numberOfTeams).map(team => team.asTeam -> 0.second) : _*)

  private def accelerateVehicles(active: Set[Team]): Unit = active.foreach(team =>
    currentSpeed(team) = vehicles(team).topSpeed.min(currentSpeed(team) + vehicles(team).acceleration)
  )

  private def updateVehiclePositions(active: Set[Team]): Unit = active.foreach(team =>
    currentPosition(team) = currentPosition(team) + (currentSpeed(team) * 2 - vehicles(team).acceleration).asDistance
  )

  private def updateVehicleTimes(active: Set[Team], seconds: Duration): Unit = active.foreach(team =>
    currentTimes(team) = seconds
  )

  private def processAdjacency(active: Set[Team]): Unit = {
    val processed = mutable.Map.empty[Team, Boolean]

    for (teamOne <- active; teamTwo <- active if teamTwo != teamOne && !processed.contains(teamOne) && !processed.contains(teamTwo)) {
      if ((currentPosition(teamOne) - currentPosition(teamTwo)).abs <= Meter(10)) {
        currentSpeed(teamOne) = currentSpeed(teamOne) * Ms(0.8f)
        processed(teamOne) = true
        currentSpeed(teamTwo) = currentSpeed(teamTwo) * Ms(0.8f)
        processed(teamTwo) = true
      }
    }
  }

  private def lastTeam: Team = currentPosition.toSeq.sortWith((a, b) => a._2 < b._2).head._1

  private def fireNitro(team: Team): Unit = if (!nitroStatus(team)) {
    nitroStatus(team) = true
    currentSpeed(team) = vehicles(team).topSpeed.min(currentSpeed(team) * 2)
  }

  private def isFinished: Boolean = currentPosition.filter(_._2 < length).isEmpty

  private def activeVehicles: Set[Team] = currentPosition.filter(_._2 < length).map(_._1).toSet

  def score: Seq[(Team, (KpH, String))] = {
    if (!isFinished) {
      throw new RuntimeException("Score is only available for finished races!")
    }

    currentTimes.toSeq.sortBy(_._2).map {

      case (team, time) =>
        team -> (currentSpeed(team).toKpH -> s"${time.toHours}h ${time.toMinutes % 60}min ${time.toSeconds % 60}sec")
    }
  }

  def race(): Unit = {
    if (isFinished) {
      throw new RuntimeException("This race has already finished!")
    }

    var seconds = 0.second
    while (!isFinished) {
      seconds += 1.second

      val active = activeVehicles
      accelerateVehicles(active)

      if (seconds.toSeconds % 2 == 0) {
        updateVehiclePositions(active)
        updateVehicleTimes(active, seconds)
        processAdjacency(active)

        fireNitro(lastTeam)
      }
    }
  }
}
