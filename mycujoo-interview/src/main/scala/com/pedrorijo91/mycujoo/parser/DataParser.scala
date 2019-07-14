package com.pedrorijo91.mycujoo.parser

import com.pedrorijo91.mycujoo.Table

trait DataParser {

  def parseData(data: String): Table // TODO what if data doesn't come as string?

}
