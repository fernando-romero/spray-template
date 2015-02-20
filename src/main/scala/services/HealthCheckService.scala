package services

import spray.routing._
import spray.http.StatusCodes._

trait HealthCheckService extends HttpService {

  val healthCheckRoute = path("hc") {
    complete(OK)
  }
}
