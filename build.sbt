name := "practical-fp-scala"

version := "0.1"

scalacOptions += "-Ymacro-annotations"

ThisBuild / scalaVersion := "2.13.0"

addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.11.0" cross CrossVersion.full)

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % "2.0.0",
  "org.typelevel" %% "cats-effect" % "2.0.0",
  "dev.profunktor" %% "console4cats" % "0.8.0",
  "io.estatico" %% "newtype" % "0.4.3",
  "eu.timepit" %% "refined" % "0.9.10"
)