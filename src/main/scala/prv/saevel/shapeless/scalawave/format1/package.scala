package prv.saevel.shapeless.scalawave

import prv.saevel.shapeless.scalawave.csv.CsvFormat
import shapeless.{::, HList, LabelledGeneric}

package object format1 {

  // TODO: Finish this with reading field sizes.
  /**
  implicit def genericFormat[A, R](implicit generic: LabelledGeneric.Aux[A, R], rFormat: Format1[R]): Format1[A] =

    new Format1[A] {

      override def serialize(a: A): Array[Byte] = rFormat.serialize(generic.to(a))

      override def deserialize(o: Array[Byte]): A = generic.from(rFormat.deserialize(o))
    }

  implicit def combineFormats[H, T <: HList](headFormat: Format1[H],
                                             tailFormat: Format1[T]): Format1[::[H, T]] =
    new CsvFormat[::[H, T]] {
      override def serialize(a: ::[H, T]): Array[Byte] = headFormat.serialize(a.head) ++ tailFormat.serialize(a.tail)

      override def deserialize(o: Array[Byte]):  = headFormat.deserialize(o) :: tailFormat.deserialize(o.tail)
    }
  */
}
