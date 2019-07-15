package com.pedrorijo91.mycujoo.parser

case class Table(name: String, fields: Set[Field])

case class Field(name: String, columnType: ColumnType)

sealed trait ColumnType {
  def nullable: Boolean
}
object ColumnType {
  def fromStr(str: String, nullable: Boolean = false): ColumnType = {
    str match {
      case "string" => SqlText(nullable)
      case "int" => SqlInt(nullable)
      case "boolean" => SqlBool(nullable)
      case _ =>
        // TODO up Optional
        throw new IllegalArgumentException("unexpeted: " + str)
    }
  }
}
case class SqlInt(nullable: Boolean) extends ColumnType
case class SqlText(nullable: Boolean) extends ColumnType
case class SqlBool(nullable: Boolean) extends ColumnType
case class SqlEnum(values: Set[String], nullable: Boolean = false) extends ColumnType