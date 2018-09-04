package prv.saevel.shapeleess.scalawave.xml

import scala.xml.Node

object XML {

  def deserialize[A](xml: Node)(implicit fromXml: XmlFormat[A]): A = fromXml.fromXml(xml)
}
