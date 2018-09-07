package prv.saevel.shapeless.scalawave

import shapeless._
import shapeless.labelled._
import shapeless.labelled.FieldType

import scala.xml._

package object xml {

  implicit val nilFormat: XmlFormat[HNil] = new XmlFormat[HNil] {

    override def serialize(a: HNil): Seq[Node] = Seq.empty

    override def deserialize(o: Seq[Node]): HNil = HNil
  }

  implicit val stringFormat = new XmlValueFormat[String] {

    override def serialize(a: String): Text = Text(a)

    override def deserialize(text: Text): String = text.data
  }

  implicit val intFormat = new XmlValueFormat[Int] {
    override def serialize(a: Int): Text = Text(a.toString)

    override def deserialize(text: Text): Int = text.data.toInt
  }

  implicit def combineFormats[K <: Symbol, H, T <: HList](implicit witness: Witness.Aux[K],
                                                          headFormat: XmlValueFormat[H],
                                                          tailFormat: XmlFormat[T]): XmlFormat[::[FieldType[K, H], T]] =
    new XmlFormat[::[FieldType[K, H], T]] {

      override def serialize(a: (::[FieldType[K, H], T])): Seq[Node] =
        Seq(Elem(null, witness.value.name, Null, TopScope, headFormat.serialize(a.head))) ++ tailFormat.serialize(a.tail)

      override def deserialize(o: Seq[Node]): (::[FieldType[K, H], T]) =
        field[K](headFormat.deserialize(Text(o.head.text))) :: tailFormat.deserialize(o.tail)
    }

  implicit def genericFormat[A, R](implicit generic: LabelledGeneric.Aux[A, R], rFormat: XmlFormat[R]): XmlFormat[A] =

    new XmlFormat[A] {

      override def serialize(a: A): Seq[Node] = rFormat.serialize(generic.to(a))

      override def deserialize(o: Seq[Node]): A = generic.from(rFormat.deserialize(o))
    }
}