package error

import scala.util.control.NoStackTrace

sealed trait BusinessError extends NoStackTrace
case object RandomError extends BusinessError

