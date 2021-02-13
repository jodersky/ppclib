import mill._, scalalib._, scalafmt._

val scala3 = "3.0.0-M3"

object lib extends ScalaModule {
  def scalaVersion = scala3
}

object example extends ScalaModule {
  def scalaVersion = scala3
  def moduleDeps = Seq(lib)
}
