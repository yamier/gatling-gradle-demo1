package simulations

import base.SimulationBase
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class JsonPostSimulation extends SimulationBase {

  object Json_String {

    val headers_json = Map("Content-Type" -> "application/json")
    val scn = scenario("json string scenario")
      .exec(http("test_json_string") //http 请求name
        .post("/computers/new") //post url
        .headers(headers_json) //设置body数据格式
        //将json参数用StringBody包起,并作为参数传递给function body()
        .body(StringBody("{\"name\":Yang Computer Test Json,\"introduced\":2020-05-13," +
          ",\"discontinued\":test,\"company\":37}")).asJSON)

  }

  object Json_File {

    val fileName = "addComputor.json"
    //注意这里,设置提交内容type
    val headers_json = Map("Content-Type" -> "application/json")
    val scn = scenario("json file scenario")
      .exec(http("test_json_file")   //http 请求name
        .post("/computers/new")     //post url
        .headers(headers_json)  //设置body数据格式
        //将json参数用StringBody包起,并作为参数传递给function body()
        .body(ElFileBody(fileName)).asJSON)

  }

  val scnJson_String = scenario("json string scenario").exec(Json_String.scn)
  val scnJson_File = scenario("json file scenario").exec(Json_File.scn)

  setUp(
    scnJson_String.inject(atOnceUsers(1)),
    scnJson_File.inject(atOnceUsers(1))
  ).protocols(httpConf)
}
