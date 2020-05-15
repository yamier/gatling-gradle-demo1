package simulations

import base.SimulationBase
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class GetComputorSimulation extends SimulationBase {

  object GetHome {
    val getHome = exec(http("GetHome").get("/"))
  }

  object Search {
    val search = exec(http("Search Computor") // let's give proper names, as they are displayed in the reports
      .get("/"))
      .pause(7)
      .exec(http("Search")
        .get("/computers?f=Macbook"))
      .pause(2)
      .exec(http("Select")
        .get("/computers/6"))
      .pause(3)
  }

  val scn = scenario("GetComputorSimulation").exec(GetHome.getHome,Search.search)

  //设置线程数
  // 一次性注入10个用户
  setUp(scn.inject(atOnceUsers(10)).protocols(httpConf))
}