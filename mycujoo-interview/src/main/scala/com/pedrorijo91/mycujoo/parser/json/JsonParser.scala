package com.pedrorijo91.mycujoo.parser.json

import com.pedrorijo91.mycujoo.parser.{DataParser, Table}
import ujson.{Arr, Obj, Str}
import ujson.Value.Value

// http://www.lihaoyi.com/post/HowtoworkwithJSONinScala.html
// http://www.lihaoyi.com/upickle/#VersionHistory

// https://circe.github.io/circe/parsing.html
class JsonParser() extends DataParser {

  import upickle.default._

  override def parseData(data: String): Table = { // TODO support other json libs
    val json: Value = ujson.read(data)
    val tableName = json("name").str
    json("fields").arr.map(elem => {
      elem("name").str
      elem("type") match { // TODO ADT
        case Str(_) => // type
        case Arr(_) => // nullable
        case Obj(_) => // enum
      }
    })

    ???
  }
}

object test { // TODO delete

  """
    |{"name":"Player","type":"record","fields":[{"name":"id","type":"int"},{"name":"firstName","type":"string"},{"name":"lastName","type":["null","string"]},{"name":"age","type":["int","null"]},{"name":"gender","type":{"type":"enum","symbols":["male","female","notSpecified"]}},{"name":"benched","type":"boolean"}]}
  """.stripMargin
}
