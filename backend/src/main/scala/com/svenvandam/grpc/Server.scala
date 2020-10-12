package com.svenvandam.grpc

import akka.actor.typed.ActorSystem
import akka.http.scaladsl.server.Directives._
import akka.grpc.scaladsl.WebHandler
import akka.http.scaladsl.server.{Route, RouteResult}
import akka.http.scaladsl.{Http2, HttpConnectionContext}
import com.svenvandam.grpc.logging.LoggingServiceImpl
import com.svenvandam.grpc.logging.generated.LoggingServiceHandler
import com.typesafe.scalalogging.StrictLogging

class Server(system: ActorSystem[Nothing], host: String, port: Int) extends StrictLogging {
  def run(): Unit = {
    implicit val sys = system.classicSystem
    implicit val ec = system.executionContext

    val grpcHandler = WebHandler.grpcWebHandler(
      LoggingServiceHandler.partial(LoggingServiceImpl)
    )

    val grpcRoute: Route = post { ctx =>
      grpcHandler(ctx.request).map(RouteResult.Complete)
    }

    val staticRoute = get {
      getFromDirectory("static") ~ // serve production frontend builds from 'static' directory
        getFromFile(
          "static/index.html"
        ) // if static file is not found, serve SPA to support client-side routing
    }

    val fullRoute = staticRoute ~ grpcRoute

    Http2()
      .bindAndHandleAsync(
        Route.asyncHandler(fullRoute),
        host,
        port,
        connectionContext = HttpConnectionContext()
      )
  }
}
