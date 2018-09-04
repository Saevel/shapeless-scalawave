package prv.saevel.shapeleess.scalawave.format1

import java.nio.ByteBuffer

trait Format1[A] {

  def toFormat1(a: A): ByteBuffer

  def fromFormat1(data: ByteBuffer): A
}
