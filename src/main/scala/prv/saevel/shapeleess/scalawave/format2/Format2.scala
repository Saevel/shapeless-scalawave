package prv.saevel.shapeleess.scalawave.format2

import java.nio.ByteBuffer

trait Format2[A] {

  def toFormat2(a: A): ByteBuffer

  def fromFormat2(data: ByteBuffer): A
}
