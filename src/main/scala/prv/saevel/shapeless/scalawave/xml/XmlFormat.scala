package prv.saevel.shapeless.scalawave.xml

import prv.saevel.shapeless.scalawave.Format
import prv.saevel.shapeless.scalawave.Format

import scala.xml.Node

trait XmlFormat[A] extends Format[A]{
  override type Output = Seq[Node]
}
