package it

import org.specs2.mutable.Specification
import spray.testkit.Specs2RouteTest
import spray.http._
import StatusCodes._
import services.TodoService
import models.todo._

class CreateTodoSpec extends Specification with Specs2RouteTest
    with TodoService {

  def actorRefFactory = system

  "POST /todos" should {
    "return Created" in {
      Post("/todos", CreateTodo("foo")) ~> todoRoute ~> check {
        status === Created
        val t = responseAs[Todo]
        t.task === "foo"
        Get(s"/todos/${t.id}") ~> todoRoute ~> check {
          responseAs[Todo] === t
        }
      }
    }
  }
}
