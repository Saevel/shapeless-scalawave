package prv.saevel.shapeless.scalawave

import shapeless._
import shapeless.{::, HList, HNil, LabelledGeneric, Witness}
import shapeless.labelled.{FieldType, field}

package object format2 {

  implicit val nilFormat2: Format2[HNil] = new Format2[HNil] {

    override def serialize(a: HNil): List[(String, String)] = List.empty

    override def deserialize(o: List[(String, String)]): HNil = HNil
  }

  implicit val intFormat2: ValueFormat2[Int] = new ValueFormat2[Int]{

    override def serialize(a: Int): String = a.toString

    override def deserialize(o: String): Int = o.toInt
  }

  implicit val stringFormat2: ValueFormat2[String] = new ValueFormat2[String]{

    override def serialize(a: String): String = a

    override def deserialize(o: String): String = o
  }

  implicit def combineFormats[K <: Symbol, H, T <: HList](implicit witness: Witness.Aux[K],
                                                          headFormat: Lazy[ValueFormat2[H]],
                                                          tailFormat: Format2[T]): Format2[::[FieldType[K, H], T]] =
    new Format2[::[FieldType[K, H], T]] {

      override def serialize(a: (::[FieldType[K, H], T])): List[(String, String)] =
        List((witness.value.name, headFormat.value.serialize(a.head))) ++ tailFormat.serialize(a.tail)

      override def deserialize(o: List[(String, String)]): (::[FieldType[K, H], T]) =
        field[K](headFormat.value.deserialize(o.head._2)) :: tailFormat.deserialize(o.tail)
    }

  implicit def genericFormat[A, R](implicit generic: LabelledGeneric.Aux[A, R], rFormat: Lazy[Format2[R]]): Format2[A] =

    new Format2[A] {

      override def serialize(a: A): List[(String, String)] = rFormat.value.serialize(generic.to(a))

      override def deserialize(o: List[(String, String)]): A = generic.from(rFormat.value.deserialize(o))
    }
}