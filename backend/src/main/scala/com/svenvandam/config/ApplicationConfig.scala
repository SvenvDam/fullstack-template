package com.svenvandam.config

import pureconfig._
import pureconfig.generic.auto._
import pureconfig.generic.semiauto._
import com.typesafe.scalalogging.StrictLogging

sealed trait Environment
object Environment {
  case object Local extends Environment
  case object Production extends Environment
}

case class ServerConfig(host: String, port: Int)

case class ApplicationConfig(
    environment: Environment,
    server: ServerConfig)

object ApplicationConfig extends StrictLogging {
  implicit val environmentConvert: ConfigReader[Environment] = deriveEnumerationReader[Environment]

  def apply(): ApplicationConfig =
    ConfigSource.default.load[ApplicationConfig] match {
      case Right(c) => c
      case Left(e) =>
        val errMsg = s"Failed to parse config file! Errors: ${e.prettyPrint()}"
        logger.error(errMsg)
        throw new RuntimeException(errMsg)
    }
}
