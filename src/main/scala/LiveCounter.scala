import cats.effect.Sync
import cats.effect.concurrent.Ref
import cats.implicits._

class LiveCounter[F[_]] private (ref: Ref[F, Int]) extends Counter[F]{
  override def inc: F[Unit] = ref.update(_ + 1)
  override def get: F[Int] = ref.get
}

object LiveCounter {
  def make[F[_]: Sync]: F[Counter[F]] = Ref.of[F, Int](0).map(new LiveCounter(_))
}