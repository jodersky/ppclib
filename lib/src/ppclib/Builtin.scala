package ppclib

/** This corresponds to the DSL's Abstract Syntax Tree. It's the canonical
  * representation of a user program. */
enum Builtin {
  case ReadCsv(name: String)
  case Map(inputs: Seq[Dataset], fn: (MapContext, Seq[Partition]) => Seq[Partition])
  case Mpc(inputs: Seq[Dataset], fn: (mpc.Context, Seq[mpc.FixedPoint]) => Seq[mpc.FixedPoint])
}
