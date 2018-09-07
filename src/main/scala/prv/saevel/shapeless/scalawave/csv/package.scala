package prv.saevel.shapeless.scalawave

import prv.saevel.shapeless.scalawave.xml.XmlFormat
import shapeless.{::, Generic, HList, HNil, LabelledGeneric, Witness}
import shapeless.labelled.FieldType

import scala.xml.Node

package object csv {

  implicit val nilFormat: CsvFormat[HNil] = new CsvFormat[HNil] {

    override def serialize(a: HNil): List[String] = List.empty

    override def deserialize(o: List[String]): HNil = HNil
  }

  implicit val intFormat: CsvFormat[Int] = new CsvFormat[Int] {

    override def serialize(a: Int): List[String] = List(a.toString)

    override def deserialize(o: List[String]): Int = o.head.toInt

  }

  implicit val stringFormat: CsvFormat[String] = new CsvFormat[String] {

    override def serialize(a: String): List[String] = List(a)

    override def deserialize(o: List[String]): String = o.head
  }

  implicit def genericFormat[A, R](implicit generic: Generic.Aux[A, R], rFormat: CsvFormat[R]): CsvFormat[A] =

    new CsvFormat[A] {

      override def serialize(a: A): List[String] = rFormat.serialize(generic.to(a))

      override def deserialize(o: List[String]): A = generic.from(rFormat.deserialize(o))
    }

  implicit def combineFormats[H, T <: HList](implicit headFormat: CsvFormat[H],
                                             tailFormat: CsvFormat[T]): CsvFormat[::[H, T]] =
    new CsvFormat[::[H, T]] {
      override def serialize(a: ::[H, T]) = headFormat.serialize(a.head) ++ tailFormat.serialize(a.tail)

      override def deserialize(o: List[String]) = headFormat.deserialize(List(o.head)) :: tailFormat.deserialize(o.tail)
    }
}
