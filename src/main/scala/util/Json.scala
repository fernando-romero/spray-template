package util

import spray.httpx.Json4sSupport
import org.json4s.Formats
import org.json4s.DefaultFormats

trait Json extends Json4sSupport {
  implicit def json4sFormats: Formats = DefaultFormats
}
