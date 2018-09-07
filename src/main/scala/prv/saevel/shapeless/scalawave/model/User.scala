package prv.saevel.shapeless.scalawave.model

import java.io.{ByteArrayInputStream, ByteArrayOutputStream, ObjectInputStream, ObjectOutputStream}

import prv.saevel.shapeless.scalawave.csv.CsvFormat
import prv.saevel.shapeless.scalawave.format1.Format1
import prv.saevel.shapeless.scalawave.format2.Format2
import prv.saevel.shapeless.scalawave.json.JsonFormat
import prv.saevel.shapeless.scalawave.xml.XmlFormat
import prv.saevel.shapeless.scalawave.csv.CsvFormat
import prv.saevel.shapeless.scalawave.format1.Format1
import prv.saevel.shapeless.scalawave.json.JsonFormat
import spray.json.{JsNumber, JsObject, JsString, JsValue}

import scala.xml.{Elem, Node}

case class User(name: String, surname: String, age: Int)

object User {

  implicit val userCsvFormat = new CsvFormat[User] {
    override def serialize(a: User): List[String] = List(a.name, a.surname, a.age.toString)

    override def deserialize(o: List[String]): User = o match {

      case Seq(name, surname, age) => User(name, surname, age.toInt)

      case other => throw new IllegalArgumentException(s"$other cannot be deserialied from CSV to User")
    }
  }

  implicit val userFormat1 = new Format1[User]{

    override def serialize(a: User): Array[Byte] = {
      val bytes = Array[Byte]()
      val byteArrayOutputStream = new ByteArrayOutputStream()
      val oos = new ObjectOutputStream(byteArrayOutputStream);
      oos.writeObject(a)
      oos.flush()
      oos.close()
      byteArrayOutputStream.toByteArray
    }

    override def deserialize(o: Array[Byte]): User = {
      val byteArrayInputStream = new ByteArrayInputStream(o)
      val objectInputStream = new ObjectInputStream(byteArrayInputStream)
      objectInputStream.readObject().asInstanceOf[User]
    }
  }

  /*
  implicit val userFormat2 = new Format2[User]{

    override def serialize(a: User): List[(String, String)] =
      List(("name", a.name), ("surname", a.surname), ("age", a.age.toString))

    override def deserialize(o: List[(String, String)]): User = o match {

      case List(("name", name), ("surname", surname), ("age", age)) => User(name, surname, age.toInt)

      case other => throw new IllegalArgumentException(s"Data: $other cannot be deserialied to a User")
    }
  }
  */

  /*
  implicit val userToXml = new XmlFormat[User] {

    override def serialize(a: User): Seq[Node] = <user name={a.name} surname = {a.surname} age = {a.age.toString}></user>

    override def deserialize(o: Seq[Node]): User = o match {
      case element: Elem if (element.label == "user") => User(
        (element \\ "@name").text,
        (element \\ "@surname").text,
        (element \\ "@age").text.toInt
      )

      case other => throw new IllegalArgumentException(s"Cannot parse XML node: $o")
    }
  }
  */

  implicit val userJsomFormat = new JsonFormat[User] {
    override def serialize(a: User): JsValue = JsObject(
      "name" -> JsString(a.name),
      "surname" -> JsString(a.surname),
      "age" -> JsNumber(a.age)
    )

    override def deserialize(o: JsValue) = o match {
      case JsObject(fields) => fields.toSeq match {
        case Seq(("name", JsString(name)), ("surname", JsString(surname)), ("age", JsNumber(age))) => User(name, surname, age.toInt)

        case other => throw new IllegalArgumentException(s"Fields $other not parseable to User class fields")
      }

      case other => throw new IllegalArgumentException(s"JSON $other not parseable to User class")
    }
  }
}
