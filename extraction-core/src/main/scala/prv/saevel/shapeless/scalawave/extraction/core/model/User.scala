package prv.saevel.shapeless.scalawave.extraction.core.model

import java.io.{ByteArrayInputStream, ByteArrayOutputStream, ObjectInputStream, ObjectOutputStream}

import prv.saevel.shapeless.scalawave.extraction.core.csv.CsvFormat
import prv.saevel.shapeless.scalawave.extraction.core.format1.Format1
import prv.saevel.shapeless.scalawave.extraction.core.json.JsonFormat
import spray.json.{JsNumber, JsObject, JsString, JsValue}

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
