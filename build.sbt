import AssemblyKeys._
import ScoverageSbtPlugin.ScoverageKeys._

name := "spray-template"

scalaVersion := "2.11.2"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

Defaults.itSettings

lazy val root = project.in(file(".")).configs(IntegrationTest)

libraryDependencies ++= {
  val akkaV = "2.3.6"
  val sprayV = "1.3.2"
  Seq(
    "io.spray"            %%  "spray-can"     % sprayV,
    "io.spray"            %%  "spray-routing" % sprayV,
    "io.spray"            %%  "spray-testkit" % sprayV    % "test",
    "io.spray"            %%  "spray-client"  % sprayV    % "it",
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
    "com.typesafe.akka"   %%  "akka-testkit"  % akkaV     % "test",
    "org.specs2"          %%  "specs2-core"   % "2.3.11"  % "test,it",
    "org.json4s"          %%  "json4s-native" % "3.2.10",
    "net.ceedubs"         %%  "ficus"         % "1.1.2",
    "com.roundeights"     %%  "hasher"        % "1.0.0"
  )
}

resolvers ++= Seq(
  "RoundEights" at "http://maven.spikemark.net/roundeights"
)

coverageMinimum := 95

coverageFailOnMinimum := true

scalariformSettings

Revolver.settings

assemblySettings

jarName in assembly := name.value + ".jar"

addCommandAlias("it", ";re-start;it:test;re-stop")

