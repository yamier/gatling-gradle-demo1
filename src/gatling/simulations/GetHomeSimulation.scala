package simulations

import base.SimulationBase
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class GetHomeSimulation extends Simulation {
  //设置请求的根路径
  val httpConf = http
    .baseURL("http://computer-database.gatling.io") // Here is the root for all relative URLs
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")
  /*
    运行10秒 during 默认单位秒
   */
  val scn = scenario("GetSimulation").during(10){
    exec(http("computer-database").get("/"))
  }

  //设置线程数
  // 一次性注入10个用户
  setUp(scn.inject(atOnceUsers(10)).protocols(httpConf))
}