package prv.saevel.shapeleess.scalawave.xml

import prv.saevel.shapeleess.scalawave.Format

import scala.xml.Node

trait XmlFormat[A] extends Format[A, Node]
