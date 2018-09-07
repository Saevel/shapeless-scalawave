package prv.saevel.shapeless.scalawave.csv

import prv.saevel.shapeless.scalawave.Format
import prv.saevel.shapeless.scalawave.Format

trait CsvFormat[A] extends Format[A]{
  override type Output = List[String]
}
