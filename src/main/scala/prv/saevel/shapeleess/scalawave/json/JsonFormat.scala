package prv.saevel.shapeleess.scalawave.json

import prv.saevel.shapeleess.scalawave.Format
import spray.json.JsValue
import spray.json.{JsonFormat => SprayJsonFormat}


trait JsonFormat[A] extends Format[A, JsValue]

object JsonFormat {

  implicit def jsonFormat[T](rootFomat: SprayJsonFormat[T]): JsonFormat[T] = new JsonFormat[T] {

    override def serialize(a: T): JsValue = rootFomat.write(a)

    override def deserialize(o: JsValue): T = rootFormat.read(o)
  }
}


