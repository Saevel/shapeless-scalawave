package prv.saevel.shapeleess.scalawave.format1

import java.nio.ByteBuffer

import prv.saevel.shapeleess.scalawave.{Format, FormatFactory}

trait Format1[A] extends Format[A, ByteBuffer]

object Format1 extends FormatFactory[ByteBuffer]
