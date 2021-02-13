import _root_.{ppclib => ppc}
//import ppclib as ppc
import ppc.Dataset

@main def run(): Unit =

  val a: Dataset = ppc.readCsv("A.csv").map{x =>
    x * 2
  }

  val b: Dataset = ppc.readCsv("B.csv").map{x =>
    ppc.debug(s"the value is $x")
    x * 2
  }

  val result: Dataset = ppc.multiparty {
    val fp: ppc.mpc.FixedPoint = b()
    ppc.mpc.reveal(a() + fp)
  }

  // do something with the result, typically you would do something more
  // involved than just printing the representation
  println(result)
