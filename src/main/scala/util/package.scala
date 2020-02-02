import cats.{Applicative, Monad}
import cats.effect.Timer

import scala.concurrent.duration._
import cats.implicits._
import io.chrisdavenport.log4cats.Logger
import retry.RetryDetails
import retry.RetryDetails.{GivingUp, WillDelayAndRetry}
import retry.RetryPolicies._

package object util {

  def retry[F[_]: Timer : Monad, A](fa: F[A]): F[A] =
    Timer[F].sleep(1.seconds) >> fa

  def logError[F[_]: Logger](action:String)(e: Throwable, details: RetryDetails): F[Unit] =
    details match {
      case r: WillDelayAndRetry =>
        Logger[F].error(s"Failed on $action. We retried ${r.retriesSoFar} times")
      case g: GivingUp =>
        Logger[F].error(s"Failed on $action after ${g.totalRetries} times. Failed with ${e.toString}")
    }

  def defaultRetryPolicy[F[_]: Applicative] =
    limitRetries[F](10) |+| exponentialBackoff[F](2.seconds)

}

