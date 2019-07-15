package example

import java.util.concurrent.Executors

import com.pedrorijo91.mycujoo.converter.sql.SqlConverter
import com.pedrorijo91.mycujoo.fetcher.http.HttpFetcher
import com.pedrorijo91.mycujoo.parser.json.JsonParser
import com.softwaremill.sttp._

import scala.concurrent.{ExecutionContext, ExecutionContextExecutor}

object Hello extends App {

  println("start")

  implicit val executor: ExecutionContextExecutor =
    ExecutionContext.fromExecutor(Executors.newFixedThreadPool(4))

  val fetcher = new HttpFetcher(sttp)
  val parser = new JsonParser()
  val converter = new SqlConverter()

  fetcher.getData()
    .map(parser.parseData)
    .map(converter.convert)
      .onComplete(println)
}
