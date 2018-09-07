package prv.saevel.shapeless.scalawave.extraction.core.xml

import prv.saevel.shapeless.scalawave.extraction.core.Format

import scala.xml.Node

trait XmlFormat[A] extends Format[A]{
  override type Output = Seq[Node]
}
