package prv.saevel.shapeless.scalawave.extraction.core.format2

import prv.saevel.shapeless.scalawave.extraction.core.Format

trait Format2[A] extends Format[A]{
  override type Output = List[(String, String)]
}

