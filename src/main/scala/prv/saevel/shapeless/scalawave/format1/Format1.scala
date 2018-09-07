package prv.saevel.shapeless.scalawave.format1

import prv.saevel.shapeless.scalawave.Format
import prv.saevel.shapeless.scalawave.Format

trait Format1[A] extends Format[A]{
  override type Output = Array[Byte]
}
