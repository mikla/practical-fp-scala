import cats.ApplicativeError
import eu.timepit.refined._
import eu.timepit.refined.api.Refined
import eu.timepit.refined.auto._
import eu.timepit.refined.types.string.NonEmptyString

import scala.io.StdIn

//import eu.timepit.refined.api.Refined
import eu.timepit.refined.collection.Contains

object RefinedApp extends App {
  type Username = String Refined Contains['g']
  val x: Username = "fg"

  type ApThrow[F[_]] = ApplicativeError[F, Throwable]
}
