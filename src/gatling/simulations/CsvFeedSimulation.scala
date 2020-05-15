package simulations

import base.SimulationBase
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class CsvFeedSimulation extends SimulationBase {

  // 动态参数
  val feeder = csv("search.csv").random

  val search = exec(
    http("Home")
      .get("/")
  ).pause(1)
    .feed(feeder)
    .exec(
      http("Search")
        .get("/computers?f=${searchCriterion}")
        .check(css("a:contains('${searchComputerName}')", "href").saveAs("computerURL"))
    )
    .pause(1)
    .exec(
      http("Select")
        .get("${computerURL}")
        .check(status.is(200))
    )
    .pause(1)

  val scn = scenario("CsvFeedSimulation").during(10) {exec(search)}

  setUp(scn.inject(atOnceUsers(10)).protocols(httpConf))

}
