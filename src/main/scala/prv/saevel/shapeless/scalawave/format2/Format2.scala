package prv.saevel.shapeless.scalawave.format2

import prv.saevel.shapeless.scalawave.Format

trait Format2[A] extends Format[A]{
  override type Output = List[(String, String)]
}

