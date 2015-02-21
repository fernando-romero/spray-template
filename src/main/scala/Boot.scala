import actors.ServiceActor
import akka.actor.Props
import akka.actor.ActorSystem
import akka.io.IO
import spray.can.Http
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._
import util.Config

object Boot extends App with Config {
  implicit val system = ActorSystem("server")
  val serviceActor = system.actorOf(Props[ServiceActor], name = "serviceActor")
  val port = config.as[Int]("http.port")
  val interface = config.as[String]("http.interface")
  implicit val timeout = Timeout(1.second)
  IO(Http) ? Http.Bind(serviceActor, interface = interface, port = port)
}
