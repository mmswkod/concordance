import sbt.Keys._
import sbt._

object ConcordanceProject extends Build with BuildExtra{
  import Resolvers._
  lazy val root = Project("concordance", file(".")) settings(coreSettings : _*)

  lazy val commonSettings: Seq[Setting[_]] = Seq(
    organization := "fail-fast",
    version := "0.1-SNAPSHOT",
    scalaVersion := "2.11.2",
    incOptions := incOptions.value.withNameHashing(true),
    ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) },

    //crossPaths := false,

  scalacOptions := Seq("-deprecation", "-unchecked", "-feature", "-language:postfixOps"),
    resolvers ++= Seq(akkaRelease, akkaSnapshot, sprayJson, sonatypeRelease, sonatypeSnapshot)
  )

  lazy val coreSettings = commonSettings ++ Seq(
    name := "concordance",
    libraryDependencies :=
      Seq(
        "com.typesafe.akka"  %% "akka-actor"       % "2.3.6",
        "com.typesafe.akka"  %% "akka-slf4j"       % "2.3.6",
        "com.typesafe.slick" %% "slick" % "2.1.0",
        "ch.qos.logback"      % "logback-classic"  % "1.0.13",
        "org.scala-lang.modules" %% "scala-xml" % "1.0.2",
        "io.spray"            %% "spray-can"        % "1.3.2",
        "io.spray"            %% "spray-caching"    % "1.3.2",
        "io.spray"            %% "spray-routing"    % "1.3.2",
        "io.spray"           %% "spray-json"       % "1.3.0",
        "commons-io" % "commons-io" % "2.4",
        "com.typesafe"         %   "config"            % "1.0.0",
        "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
        "org.scalautils" % "scalautils_2.11" % "2.1.7",
        "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test"
      ),

    parallelExecution in Test := false,

    unmanagedResources in Compile <+= baseDirectory map { _ / "LICENSE" }

  )
}


object Resolvers {
  val akkaRelease = "typesafe release repo" at "http://repo.typesafe.com/typesafe/releases/"
  val akkaSnapshot = "typesafe snapshot repo" at "http://repo.typesafe.com/typesafe/snapshots/"
  val sprayJson = "spray" at "http://repo.spray.io/"
  val sonatypeSnapshot = "Sonatype Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"
  val sonatypeRelease = "Sonatype releases"  at "https://oss.sonatype.org/content/repositories/releases/"


}
