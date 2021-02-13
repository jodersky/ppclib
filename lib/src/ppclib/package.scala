package ppclib

def debug(message: String)(using ctx: MapContext): Unit = ctx.debug(message)

def readCsv(name: String): Dataset = Dataset(Builtin.ReadCsv(name))

inline def multiparty(inline fn: mpc.Context ?=> mpc.FixedPoint): Dataset = ${multipartyImpl('fn)}
