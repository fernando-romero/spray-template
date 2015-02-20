package models.todo

import java.util.Date
import scala.util.Random
import com.roundeights.hasher.Implicits._
import scala.util.Try

object Todo {

  object Statuses {
    val Pending = "pending"
    val InProgress = "in progress"
    val Canceled = "canceled"
    val Done = "done"
    val all = List(Pending, InProgress, Canceled, Done)
  }

  def isValidStatus(status: String) = Statuses.all.contains(status)

  def apply(c: CreateTodo): Try[Todo] = Try {
    if (c.task.size > 140) throw new Exception("task too long")
    Todo(task = c.task)
  }
}

case class Todo(
    id: String = new Random(System.nanoTime).nextString(32).md5.hex,
    task: String,
    status: String = Todo.Statuses.Pending,
    createdAt: Date = new Date(),
    updatedAt: Date = new Date()) {

  def update(u: UpdateTodo): Try[Todo] = Try {
    val newTask = u.task.getOrElse(this.task)
    val newStatus = u.status.getOrElse(this.status)
    if (newTask.size > 140) throw new Exception("task too long")
    if (!Todo.isValidStatus(newStatus)) throw new Exception("invalid status")
    this.copy(task = newTask, status = newStatus)
  }
}
