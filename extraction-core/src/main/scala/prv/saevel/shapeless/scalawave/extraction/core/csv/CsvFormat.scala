package prv.saevel.shapeless.scalawave.extraction.core.csv

import prv.saevel.shapeless.scalawave.extraction.core.Format

trait CsvFormat[A] extends Format[A]{
  override type Output = List[String]
}
