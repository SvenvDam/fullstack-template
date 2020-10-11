import sbt._

object Dependencies {
  lazy val common = Seq(
    "com.typesafe"          % "config"            % "1.3.3",
    "com.github.pureconfig" %% "pureconfig"       % "0.13.0",
    "io.grpc"               % "grpc-netty-shaded" % "1.25.0",
    "ch.megard"             %% "akka-http-cors"   % "0.4.2"
  )

  lazy val logging = Seq(
    "com.typesafe.scala-logging" %% "scala-logging"   % "3.9.2",
    "ch.qos.logback"             % "logback-classic"  % "1.2.3",
    "org.slf4j"                  % "log4j-over-slf4j" % "1.7.30"
  )

  lazy val scalatest = "org.scalatest" %% "scalatest" % "3.0.8" % Test
}
