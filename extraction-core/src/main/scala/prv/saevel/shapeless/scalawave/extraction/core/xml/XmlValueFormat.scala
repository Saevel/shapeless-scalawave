package prv.saevel.shapeless.scalawave.extraction.core.xml

import scala.xml.Text

trait XmlValueFormat[A] {

  def serialize(a: A): Text

  def deserialize(text: Text): A
}
