package prv.saevel.shapeless.scalawave.extraction.core.json

import prv.saevel.shapeless.scalawave.extraction.core.Format
import spray.json.JsValue
import spray.json.{JsonFormat => SprayJsonFormat}


trait JsonFormat[A] extends Format[A]{
  override type Output = JsValue
}

object JsonFormat {

  implicit def jsonFormat[T](implicit rootFormat: SprayJsonFormat[T]): JsonFormat[T] = new JsonFormat[T] {

    override def serialize(a: T): JsValue = rootFormat.write(a)

    override def deserialize(o: JsValue): T = rootFormat.read(o)
  }
}

