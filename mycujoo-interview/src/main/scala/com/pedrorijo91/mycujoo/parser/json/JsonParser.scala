package com.pedrorijo91.mycujoo.parser.json

import com.pedrorijo91.mycujoo.parser._
import ujson.Value.Value
import ujson.{Arr, Obj, Str}

class JsonParser() extends DataParser[String] {

  override def parseData(data: String): Table = { // TODO support other json libs
    val json: Value = ujson.read(data)

    val tableName = json("name").str

    val fields = json("fields").arr.map(elem => {
      val columnName = elem("name").str
      val columnType = elem("type") match {
        case Str(colType) =>
          ColumnType.fromStr(colType)
        case Arr(arr) =>
          ColumnType.fromStr(arr.find(_.str != "null").get.str, nullable = true) // TODO .get
        case Obj(typeDef) =>
          SqlEnum(typeDef("symbols").arr.map(_.str).toSet)
        case any =>
          throw new IllegalArgumentException("unexpected: " + any)
      }

      Field(columnName, columnType)
    }).toSet


    Table(tableName, fields)
  }
}