package util

import com.typesafe.config._
import net.ceedubs.ficus.Ficus._
import net.ceedubs.ficus._

trait Config extends FicusInstances {
  val config: FicusConfig = ConfigFactory.load
}
