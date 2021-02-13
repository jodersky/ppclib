package ppclib

class Dataset(parent: Builtin) {

  // user-level function
  def map(fn: MapContext ?=> Partition => Partition): Dataset = {
    val b = Builtin.Map(
      Seq(this),
      (ctx, partitions) => Seq(fn(using ctx)(partitions.head))
    )
    Dataset(b)
  }

  def apply()(using mpc.Context): mpc.FixedPoint = ???

  override def toString = s"Dataset($parent)"

}
