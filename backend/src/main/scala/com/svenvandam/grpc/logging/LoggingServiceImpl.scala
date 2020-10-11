package com.svenvandam.grpc.logging

import com.typesafe.scalalogging.StrictLogging
import com.svenvandam.grpc.logging.generated.{Level, LogMessage, Logged, LoggingService}
import scala.concurrent.Future

class LoggingServiceImpl extends LoggingService with StrictLogging {
  override def log(in: LogMessage): Future[Logged] = in match {
    case LogMessage(Level.DEBUG, msg, _, _) =>
      logger.debug(msg)
      Future.successful(Logged.defaultInstance)

    case LogMessage(Level.INFO, msg, _, _) =>
      logger.info(msg)
      Future.successful(Logged.defaultInstance)

    case LogMessage(Level.WARN, msg, _, _) =>
      logger.warn(msg)
      Future.successful(Logged.defaultInstance)

    case LogMessage(Level.ERROR, msg, _, _) =>
      logger.error(msg)
      Future.successful(Logged.defaultInstance)

    case msg => Future.failed(new Exception(s"Could not handle log message: $msg"))
  }
}
