

ThisBuild / scalaVersion     := "2.13.0"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "mycujoo-interview",
    resolvers += Resolver.bintrayRepo("freshwood", "maven"),

    libraryDependencies ++= Seq(
      "com.softwaremill.sttp" %% "core" % "1.6.2",
      "com.lihaoyi" %% "upickle" % "0.7.5"
    )
  )



