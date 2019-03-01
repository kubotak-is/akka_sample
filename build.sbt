import sbt._
import sbt.Keys.{libraryDependencies, organization, resolvers}
import sbtassembly.AssemblyPlugin.autoImport.assemblyMergeStrategy

lazy val commonSettings = Seq(
  organization := "jp.co.kubotak",
  scalaVersion := "2.11.12",
  resolvers ++= Seq(
    "clojars" at "https://clojars.org/repo",
    "conjars" at "http://conjars.org/repo",
    "plugins" at "http://repo.spring.io/plugins-release",
    "sonatype" at "http://oss.sonatype.org/content/groups/public/",
    "mvn" at "https://mvnrepository.com/artifact"
  ),

  libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-actor" % "2.5.20",
    "com.typesafe.akka" %% "akka-remote" % "2.5.20",
    "com.typesafe.akka" %% "akka-testkit" % "2.5.20" % Test,
    "com.typesafe.akka" %% "akka-stream" % "2.5.20",
    "com.typesafe.akka" %% "akka-stream-testkit" % "2.5.20" % Test,
    "com.typesafe.akka" %% "akka-http" % "10.1.7",
    "com.typesafe.akka" %% "akka-http-testkit" % "10.1.7" % Test
  ),

  assemblyMergeStrategy in assembly := {
    case PathList("org", "aopalliance", xs@_*) => MergeStrategy.last
    case PathList("javax", "inject", xs@_*) => MergeStrategy.last
    case PathList("javax", "servlet", xs@_*) => MergeStrategy.last
    case PathList("javax", "activation", xs@_*) => MergeStrategy.last
    case PathList("org", "apache", xs@_*) => MergeStrategy.last
    case PathList("com", "google", xs@_*) => MergeStrategy.last
    case PathList("com", "microsoft", xs@_*) => MergeStrategy.last
    case PathList("com", "typesafe", xs@_*) => MergeStrategy.last
    case PathList("com", "softwaremill", xs@_*) => MergeStrategy.last
    case "about.html" => MergeStrategy.rename
    case "META-INF/ECLIPSEF.RSA" => MergeStrategy.last
    case "META-INF/mailcap" => MergeStrategy.last
    case "META-INF/mimetypes.default" => MergeStrategy.last
    case "plugin.properties" => MergeStrategy.last
    case "log4j.properties" => MergeStrategy.last
    case x =>
      val oldStrategy = (assemblyMergeStrategy in assembly).value
      oldStrategy(x)
  },
  assemblyJarName := {
    s"${name.value}-${version.value}.jar"
  },
)

lazy val bootPay = (project in file("bootPay"))
  .settings(commonSettings: _*)
  .settings(
    name := "boot-pay",
    version := "0.1",
    mainClass in assembly := Some("jp.co.kubotak.BootPay")
  )

lazy val remotePay = (project in file("remotePay"))
  .settings(commonSettings: _*)
  .settings(
    name := "remote-pay",
    version := "0.1",
    mainClass in assembly := Some("jp.co.kubotak.RemotePay")
  )

lazy val superVisor = (project in file("superVisor"))
  .settings(commonSettings: _*)
  .settings(
    name := "super-visor",
    version := "0.1",
    mainClass in assembly := Some("jp.co.kubotak.SuperVisor")
  )
