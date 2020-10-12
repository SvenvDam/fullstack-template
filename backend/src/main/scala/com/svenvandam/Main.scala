package com.svenvandam

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import com.svenvandam.config.ApplicationConfig
import com.svenvandam.grpc.Server

object Main {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem(Behaviors.empty, "Backend")

    val config = ApplicationConfig()

    new Server(system, config.server.host, config.server.port).run()
  }
}
