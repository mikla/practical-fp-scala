package service

import cats.MonadError

class LiveCategories[F[_] : MonadError[*[_], Throwable]] extends Categories[F] {
  override def findAll: F[List[domain.Category]] = ???
}
