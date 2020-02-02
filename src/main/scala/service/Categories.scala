package service

import domain.Category

trait Categories[F[_]] {
  def findAll: F[List[Category]]
}
