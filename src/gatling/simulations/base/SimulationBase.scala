package base

import io.gatling.core.Predef._
import io.gatling.http.Predef.http

class SimulationBase extends Simulation {

  val host: String = "http://computer-database.gatling.io"

  val httpConf = http.baseURL(host)

  val thinkTime: Int = 5
}