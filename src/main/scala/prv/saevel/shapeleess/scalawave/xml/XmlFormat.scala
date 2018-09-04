package prv.saevel.shapeleess.scalawave.xml

import scala.xml.Node

trait XmlFormat[A] {

  def toXml(a: A): Node

  def fromXml(node: Node): A
}
