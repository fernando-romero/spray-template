package providers

import models.todo._
import scala.util.Try

trait TodoProvider {

  private val todos = scala.collection.mutable.ListBuffer.empty[Todo]

  def getTodos = todos

  def getTodo(id: String) = todos.find(_.id == id)

  def createTodo(c: CreateTodo) = Todo(c).map { t =>
    todos += t
    t
  }

  def updateTodo(id: String, u: UpdateTodo) = todos.find(_.id == id).map { t =>
    t.update(u)
  }

  def deleteTodo(id: String) = todos.find(_.id == id).map { t =>
    todos -= t
  }
}
