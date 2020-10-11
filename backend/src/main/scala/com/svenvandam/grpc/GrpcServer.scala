package com.svenvandam.grpc

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.grpc.scaladsl.WebHandler
import akka.http.scaladsl.{Http, HttpConnectionContext}
import com.svenvandam.grpc.logging.LoggingServiceImpl
import com.svenvandam.grpc.logging.generated.LoggingServiceHandler

class GrpcServer(system: ActorSystem, host: String, port: Int) {
  def run(): Unit = {
    implicit val sys = system
    implicit val mat = ActorMaterializer()
    implicit val ec = system.dispatcher

    val service = WebHandler.grpcWebHandler(
      LoggingServiceHandler.partial(new LoggingServiceImpl())
    )

    Http()
      .bindAndHandleAsync(
        service,
        host,
        port,
        connectionContext = HttpConnectionContext()
      )
  }
}
