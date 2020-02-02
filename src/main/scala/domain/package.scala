import eu.timepit.refined.types.string.NonEmptyString
import io.estatico.newtype.macros.newtype
import io.estatico.newtype.macros._

package object domain {

  @newtype case class Brand(value: NonEmptyString)
  @newtype case class Category(value: NonEmptyString)

}
