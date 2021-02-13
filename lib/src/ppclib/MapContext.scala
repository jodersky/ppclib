package ppclib

@annotation.implicitNotFound("This operation is only allowed within a map.")
trait MapContext {
  def debug(message: String): Unit // the runtime will implement this
}
