name := """slickProject"""
organization := "herbert"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.6"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
libraryDependencies += "com.typesafe.play" %% "play-slick" % "5.0.0"
libraryDependencies += "com.typesafe.slick" %% "slick-codegen" % "3.3.3"
libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.23"
libraryDependencies += "org.postgresql" % "postgresql" % "42.2.24"
libraryDependencies += "com.github.t3hnar" %% "scala-bcrypt" % "4.1"
libraryDependencies += "com.typesafe.slick" %% "slick-hikaricp" % "3.3.3"


// Adds additional packages into Twirl
//TwirlKeys.templateImports += "herbert.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "herbert.binders._"
Global / semanticdbEnabled := true