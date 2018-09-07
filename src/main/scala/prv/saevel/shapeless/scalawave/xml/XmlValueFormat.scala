package prv.saevel.shapeless.scalawave.xml

import scala.xml.Text

trait XmlValueFormat[A] {

  def serialize(a: A): Text

  def deserialize(text: Text): A
}
