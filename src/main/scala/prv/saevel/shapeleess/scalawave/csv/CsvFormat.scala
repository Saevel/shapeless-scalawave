package prv.saevel.shapeleess.scalawave.csv

import prv.saevel.shapeleess.scalawave.Format

trait CsvFormat[A] extends Format[A, List[String]]
