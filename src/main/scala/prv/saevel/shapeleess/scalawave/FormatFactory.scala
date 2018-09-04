package prv.saevel.shapeleess.scalawave

trait FormatFactory[Output] {

  def serialize[A](a: A)(implicit format: Format[A, Output]): Output = format.serialize(a)

  def deserialize[A](o: Output)(implicit format: Format[A, Output]): A = format.deserialize(o)
}
