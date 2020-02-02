import cats.effect.{IO, Sync, Timer}
import cats.{Applicative, Monad, MonadError}
import io.chrisdavenport.log4cats.Logger
import retry._
import cats.implicits._
import cats.effect.implicits._

import scala.concurrent.ExecutionContext.Implicits.global
import io.chrisdavenport.log4cats.slf4j.Slf4jLogger

import scala.concurrent.duration._
import scala.io.StdIn

object PaymentRetryApp extends App {

  def pay[F[_]](implicit ME: MonadError[F, Throwable]): F[Unit] =
    if ((Math.random() * 100).toLong % 2 == 0) ME.raiseError(new Exception("No connection"))
    else ME.pure(())

  def processPayment[F[_] : Sleep : Applicative : Logger : MonadError[*[_], Throwable] : Timer](): F[Unit] = {
    val action = retryingOnAllErrors[Unit](
      policy = util.defaultRetryPolicy[F],
      onError = util.logError[F]("Payments")
    )(pay[F])

    action.adaptError {
      case e => new Exception(s"adapted exception ${e}")
    }.onError {
      case _ =>
          Logger[F].error(s"Failed to create payment. ") *> Timer[F].sleep(10.seconds) *> action
    }
  }

  implicit val ioTimer = IO.timer(scala.concurrent.ExecutionContext.Implicits.global)
  implicit def unsafeLogger[F[_]: Sync] = Slf4jLogger.getLogger[F]

  processPayment[IO]().unsafeRunSync()
}