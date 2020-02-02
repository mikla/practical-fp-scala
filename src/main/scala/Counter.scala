trait Counter[F[_]] {
  def inc: F[Unit]
  def get: F[Int]
}
