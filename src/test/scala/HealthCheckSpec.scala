package test

import org.specs2.mutable.Specification
import spray.testkit.Specs2RouteTest
import spray.http._
import StatusCodes.OK
import services.HealthCheckService

class HealthCheckSpec extends Specification with Specs2RouteTest
    with HealthCheckService {

  def actorRefFactory = system

  "HealthCheckService" should {
    "returns OK" in {
      Get("/hc") ~> healthCheckRoute ~> check {
        status === OK
      }
    }
  }
}
