package prv.saevel.shapeless.scalawave.format2

trait ValueFormat2[T] {

  def serialize(t: T): String

  def deserialize(s: String): T
}
