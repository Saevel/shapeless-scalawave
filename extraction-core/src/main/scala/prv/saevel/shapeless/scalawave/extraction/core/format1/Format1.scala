package prv.saevel.shapeless.scalawave.extraction.core.format1

import prv.saevel.shapeless.scalawave.extraction.core.Format

trait Format1[A] extends Format[A]{
  override type Output = Array[Byte]
}
