import scoverage.ScoverageKeys

name := "lib-aws-codedeploy"

organization := "io.github.sullis"

scalaVersion := "2.12.8"

crossScalaVersions := Seq(scalaVersion.value)

val awsLibVersion = "2.5.20"

scalacOptions += "-target:jvm-1.8"

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

ScoverageKeys.coverageFailOnMinimum := true

ScoverageKeys.coverageMinimum := 15.0

libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-java8-compat" % "0.9.0",
  "com.gilt" %% "gfc-logging" % "0.0.8"
, "com.gilt" %% "gfc-concurrent" % "0.3.8"
, "software.amazon.awssdk" % "codedeploy" % awsLibVersion
, "org.scalatest" %% "scalatest" % "3.0.7" % Test
)

releaseCrossBuild := true

releasePublishArtifactsAction := PgpKeys.publishSigned.value

publishMavenStyle := true

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

licenses := Seq("Apache-style" -> url("https://raw.githubusercontent.com/sullis/lib-aws-cloudwatch/master/LICENSE"))

homepage := Some(url("https://github.com/sullis/lib-aws-cloudwatch"))

pomExtra := <scm>
  <url>https://github.com/sullis/lib-aws-cloudwatch.git</url>
  <connection>scm:git:git@github.com:sullis/lib-aws-cloudwatch.git</connection>
</scm>
<developers>
  <developer>
    <id>sullis</id>
    <name>Sean Sullivan</name>
    <url>https://github.com/sullis</url>
  </developer>
</developers>
