package com.svenvandam

import akka.actor.ActorSystem
import com.svenvandam.config.ApplicationConfig
import com.svenvandam.grpc.GrpcServer

object Main {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("Backend")

    val config = ApplicationConfig()

    new GrpcServer(system, config.server.host, config.server.port).run()
  }
}
