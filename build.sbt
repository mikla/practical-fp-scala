name := "practical-fp-scala"

version := "0.1"

scalacOptions += "-Ymacro-annotations"

ThisBuild / scalaVersion := "2.13.0"

addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.11.0" cross CrossVersion.full)

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % "2.0.0",
  "com.github.cb372" %% "cats-retry" % "1.1.0",
  "org.typelevel" %% "cats-effect" % "2.0.0",
  "dev.profunktor" %% "console4cats" % "0.8.0",
  "io.estatico" %% "newtype" % "0.4.3",
  "eu.timepit" %% "refined" % "0.9.10",
  "io.chrisdavenport" %% "log4cats-core"    % "1.0.1",  // Only if you want to Support Any Backend
  "io.chrisdavenport" %% "log4cats-slf4j"   % "1.0.1" , // Direct Slf4j Support - Recommended
 "ch.qos.logback" % "logback-classic" % "1.2.3"
)