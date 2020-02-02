package service

import cats.{Applicative, MonadError}
import cats.implicits._
import eu.timepit.refined.types.string.NonEmptyString
import eu.timepit.refined._
import io.estatico.newtype.macros._

class LiveCategories[F[_] : MonadError[*[_], Throwable] : Applicative] extends Categories[F] {
  override def findAll: F[List[domain.Category]] = Applicative[F].pure(
    List(
      domain.Category(NonEmptyString("e"))
    )
  )
}
