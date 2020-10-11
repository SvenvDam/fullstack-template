import Dependencies._

enablePlugins(AkkaGrpcPlugin)

name := "backend"

version := "0.1"

scalaVersion := "2.13.3"

libraryDependencies ++= common ++ logging :+ scalatest

akkaGrpcGeneratedSources := Seq(AkkaGrpc.Server)
PB.protoSources in Compile := Seq(file("../proto"))

assemblyMergeStrategy in assembly := {
  case x if Assembly.isConfigFile(x) => MergeStrategy.concat
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case _                             => MergeStrategy.last
}
