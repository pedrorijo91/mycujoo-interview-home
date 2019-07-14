package com.pedrorijo91.mycujoo.converter

import com.pedrorijo91.mycujoo.parser.Table

trait DataConverter {

  def convert(table: Table): SqlStm // TODO what if NoSQL

}
