package com.pedrorijo91.mycujoo.fetcher.http

import com.pedrorijo91.mycujoo.fetcher.DataFetcher
import com.pedrorijo91.mycujoo.fetcher.http.HttpFetcher.HttpClient
import com.softwaremill.sttp.{Empty, HttpURLConnectionBackend, Id, Request, RequestT, Response}
import com.softwaremill.sttp._

import scala.concurrent.{ExecutionContext, Future}

class HttpFetcher(private val client: HttpClient, private val uri: Uri = HttpFetcher.DefaultUrl) extends DataFetcher { // TODO what if another http client?

  override def getData()(implicit executionContext: ExecutionContext): Future[String] = {
    val request = sttp.get(uri)

    implicit val backend: SttpBackend[Id, Nothing] = HttpURLConnectionBackend() // TODO async banckend
    val rsp: Response[String] = request.send()

    rsp.body match {
      case Left(err) => Future.failed(new RuntimeException(err))
      case Right(json) => Future.successful(json)
    }
  }

}

object HttpFetcher {
  type HttpClient = RequestT[Empty, String, Nothing]

  val DefaultUrl = uri"https://s3-eu-west-1.amazonaws.com/mycujoo-assignments/be-assignment/player.json"
}