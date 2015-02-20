package actors

import akka.actor.Actor
import services._

class ServiceActor extends Actor with HealthCheckService with TodoService {

  def actorRefFactory = context
  def receive = runRoute(route)
  def route = healthCheckRoute ~ todoRoute
}
