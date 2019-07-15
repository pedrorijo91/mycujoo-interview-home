package com.pedrorijo91.mycujoo.converter.sql

import com.pedrorijo91.mycujoo.converter.{DataConverter, SqlStm}
import com.pedrorijo91.mycujoo.parser._

class SqlConverter extends DataConverter {

  override def convert(table: Table): SqlStm = {

    val columns = table.fields.map { field =>

      s"""
        | ${field.name} ${convertColumnType(field.columnType)} ${if(field.columnType.nullable) "" else "NOT NULL"}
      """.stripMargin.trim

    }.mkString(",\n")

    SqlStm(s"""
      | CREATE TABLE ${table.name} (
      |   $columns
      | );
    """.stripMargin)
  }

  private def convertColumnType(columnType: ColumnType): String = {
    columnType match {
      case SqlInt(_) => "INT"
      case SqlText(_) => "TEXT"
      case SqlBool(_) => "BOOLEAN"
      case SqlEnum(values, _) => s"ENUM(${values.map(v => s"'$v'").mkString(", ")})"
    }
  }
}
