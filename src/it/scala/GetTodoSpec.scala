package it

import org.specs2.mutable.Specification
import spray.testkit.Specs2RouteTest
import spray.http._
import StatusCodes._
import services.TodoService
import models.todo._

class GetTodoSpec extends Specification with Specs2RouteTest
    with TodoService {

  def actorRefFactory = system

  "GET /todos/:id" should {
    "return NotFound when todo not found" in {
      Get("/todos/foo") ~> todoRoute ~> check {
        status === NotFound
      }
    }
    "return OK when todo is found" in {
      Post("/todos", CreateTodo("foo")) ~> todoRoute ~> check {
        val t = responseAs[Todo]
        Get(s"/todos/${t.id}") ~> todoRoute ~> check {
          status === OK
          responseAs[Todo] === t
        }
      }
    }
  }
}
