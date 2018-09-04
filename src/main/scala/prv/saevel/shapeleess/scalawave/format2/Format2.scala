package prv.saevel.shapeleess.scalawave.format2

import java.nio.ByteBuffer

import prv.saevel.shapeleess.scalawave.{Format, FormatFactory}

trait Format2[A] extends Format[A, ByteBuffer]

object Format2 extends FormatFactory[ByteBuffer]
