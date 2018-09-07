package prv.saevel.shapeless.scalawave

trait Format[A] {

  type Output

  def serialize(a: A): Output

  def deserialize(o: Output): A
}

object Format {
  type Aux[A, O] = Format[A]{type Output = O}
}
