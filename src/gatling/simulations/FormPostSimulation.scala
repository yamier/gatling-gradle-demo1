package simulations

import base.SimulationBase
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class FormPostSimulation extends SimulationBase {

  object GetHome {
    val getHome = exec(http("GetHome").get("/"))
  }

  object FormPost {
    val edit = exec(
      http("FormPost")
        .get("/computers/new")
    ).pause(1)
      .exec(
        http("Post")
          .post("/computers")
          .formParam("name", "Yang Computer Test Form")
          .formParam("introduced", "2020-05-13")
          .formParam("discontinued", "")
          .formParam("company", "37")
      )
  }

  val scnGetHome = scenario("GetHomeSimulation").exec(GetHome.getHome)
  val scnFormPost = scenario("FormPostSimulation").exec(FormPost.edit)
  
  setUp(
    scnGetHome.inject(rampUsers(10) over(10)),
    scnFormPost.inject(rampUsers(2) over(10))
  ).protocols(httpConf)
}