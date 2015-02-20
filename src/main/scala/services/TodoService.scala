// format: OFF

package services

import actors._
import spray.routing._
import models.todo._
import spray.http.StatusCodes._
import scala.util.Success
import scala.util.Failure
import providers.TodoProvider
import util.Json

trait TodoService extends HttpService with TodoProvider with Json {

  val todoRoute = pathPrefix("todos") {
    pathEnd {
      get {
        complete(getTodos)
      } ~
      (post & entity(as[CreateTodo])) { c =>
        createTodo(c) match {
          case Failure(e) => complete(BadRequest, e.getMessage)
          case Success(t) => complete(Created, t)
        }
      }
    } ~
    path(Segment) { id =>
      get {
        getTodo(id) match {
          case None => complete(NotFound)
          case Some(t) => complete(t)
        }
      } ~
      delete {
        deleteTodo(id) match {
          case None => complete(NotFound)
          case Some(_) => complete(NoContent)
        }
      } ~
      (put & entity(as[UpdateTodo])) { u =>
        updateTodo(id, u) match {
          case None => complete(NotFound)
          case Some(Failure(e)) => complete(BadRequest, e.getMessage)
          case Some(Success(tu)) => complete(tu)
        }
      }
    }
  }
}
