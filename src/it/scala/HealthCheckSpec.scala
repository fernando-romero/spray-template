package it

import org.specs2.mutable.Specification
import spray.http._
import spray.client.pipelining._
import StatusCodes.OK
import akka.actor.ActorSystem
import scala.concurrent.Future
import util.Config

class HealthCheckSpec extends Specification with Config {

  val port = config.as[Int]("http.port")
  val interface = config.as[String]("http.interface")
  val url = s"http://$interface:$port/hc"

  implicit val system = ActorSystem()
  import system.dispatcher
  val pipeline: HttpRequest => Future[HttpResponse] = sendReceive

  "HealthCheckService" should {
    "return OK" in {
      val respFut = pipeline(Get(url))
      respFut.map { r =>
        r.status === OK
      } await
    }
  }
}
