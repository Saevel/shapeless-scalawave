package prv.saevel.shapeless.scalawave.spark.extractor

import cats.Id
import frameless.TypedDataset
import org.apache.spark.sql.SparkSession
import prv.saevel.shapeless.scalawave.extraction.core.Conversion
import prv.saevel.shapeless.scalawave.extraction.core.model.User
import frameless.SparkDelay

object SparkExtractorApplication extends App {

  import prv.saevel.shapeless.scalawave.extraction.core.csv._
  import prv.saevel.shapeless.scalawave.extraction.core.format2._

  private implicit val spark = SparkSession.builder().master("local[*]").appName("DemoApplication").getOrCreate

  private implicit val sqlContext = spark.sqlContext

  private implicit val delay = new SparkDelay[Id] {
    override def delay[A](a: => A)(implicit spark: SparkSession): Id[A] = a
  }

  import spark.implicits._

  val users = Seq(
    List("Kamil", "Owczarek", "28"),
    List("Artur", "Wolyniec", "27"),
    List("Iwona", "Owczarek", "54"),
    List("Leszek", "Owczarek", "58")
  )

  private val dataset = TypedDataset.create(users)

  private val converterFunction = Conversion.conversion[User, List[String], CsvFormat[User], List[(String, String)], Format2[User]]

  println(s"Hello, Frameless")

  private val results = dataset.map(converterFunction)
    .map(_.map{ case (name, value) => s"$name:$value"})
    .map(_.mkString(","))
    .show[Id]()
}
