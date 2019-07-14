package com.pedrorijo91.mycujoo.fetcher

import scala.concurrent.{ExecutionContext, Future}

trait DataFetcher {

  def getData()(implicit executionContext: ExecutionContext): Future[String] // TODO what if not json?

}
