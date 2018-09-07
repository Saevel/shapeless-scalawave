package prv.saevel.shapeless.scalawave.http.extractor

import scala.concurrent.ExecutionContext.Implicits.global

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives
import akka.stream.ActorMaterializer
import prv.saevel.shapeless.scalawave.extraction.core.Conversion
import prv.saevel.shapeless.scalawave.extraction.core.model.User
import spray.json.{DefaultJsonProtocol, JsValue}

import scala.util.{Failure, Success}
import scala.xml.Node

object HttpExtractorApplication extends App with DefaultJsonProtocol with Directives with SprayJsonSupport {

  import prv.saevel.shapeless.scalawave.extraction.core.xml._
  import prv.saevel.shapeless.scalawave.extraction.core.json._

  implicit val userFormat = jsonFormat3(User)

  implicit val system = ActorSystem()

  implicit val materializer = ActorMaterializer()

  private val converterFunction = Conversion.conversion[User, Seq[Node], XmlFormat[User], JsValue, JsonFormat[User]]

  private val usersXml: Seq[Seq[Node]] = Seq(
    User("Kamil", "Owczarek", 28),
    User("Artur", "Wolyniec", 27),
    User("Iwona", "Owczarek", 54),
    User("Leszek", "Owczarek", 58)
  ).map(implicitly[XmlFormat[User]].serialize)

  Http().bindAndHandle(interface = "127.0.0.1", port = 8080, handler = path(""){
    complete(usersXml.map(converterFunction).map(_.toString).mkString("[", ", ", "]"))
  }) onComplete {
    case Success(binding) => println("HTTP interface running")
    case Failure(e) => {
      e.printStackTrace
      System.exit(-1)
    }
  }
}
