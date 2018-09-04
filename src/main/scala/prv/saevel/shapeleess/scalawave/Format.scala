package prv.saevel.shapeleess.scalawave

trait Format[A, Output] {

  def serialize(a: A): Output

  def deserialize(o: Output): A
}
