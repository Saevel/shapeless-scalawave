package prv.saevel.shapeless.scalawave.extraction.core

import prv.saevel.shapeless.scalawave.extraction.core.json.JsonFormat
import prv.saevel.shapeless.scalawave.extraction.core.model.User
import spray.json.JsValue

import scala.xml.Node

object Application extends App {

  println("Hello, Shapeless!")

  import prv.saevel.shapeless.scalawave.extraction.core.csv._

  import prv.saevel.shapeless.scalawave.extraction.core.xml._

  import prv.saevel.shapeless.scalawave.extraction.core.format2._

  val xmlToJson = Conversion.conversion[User, Seq[Node], XmlFormat[User], JsValue, JsonFormat[User]]

  val xmlToCsv = Conversion.conversion[User, Seq[Node], XmlFormat[User], List[String], CsvFormat[User]]

  val csvToFormat2 = Conversion.conversion[User, List[String], CsvFormat[User], List[(String, String)], Format2[User]]

  val inputUser = User("Kamil", "Owczarek", 28)

  val userXml = implicitly[XmlFormat[User]].serialize(inputUser)

  val userCsv = List("Kamil", "Owczarek", "28")

  println(s"XML => JSON: ${xmlToJson(userXml).toString}")

  println(s"XML => CSV: ${xmlToCsv(userXml).mkString(", ")}")

  println(s"CSV => Format2: ${csvToFormat2(userCsv).mkString(", ")}")
}