package com.pedrorijo91.mycujoo.parser

trait DataParser[T] {

  def parseData(data: T): Table

}
