package ppclib

import scala.quoted._

def multipartyImpl
  (using qctx: quoted.Quotes)
  (expr: Expr[mpc.Context ?=> mpc.FixedPoint]): Expr[Dataset] = {
  import qctx.reflect._

  // find all calls to dataset.apply()
  object finder extends TreeAccumulator[List[Expr[Dataset]]] {
    def foldTree(acc: List[Expr[Dataset]], tree: Tree)(owner: Symbol) = {
      tree match {
        case Apply(Select(term, "apply"), _) if term.tpe <:< TypeRepr.of[Dataset] =>
          term.asExprOf[Dataset] :: acc
        case _ =>
          foldOverTree(acc, tree)(owner)
      }
    }
  }

  val inputExprs: List[Expr[Dataset]] =
    finder.foldTree(Nil, expr.asTerm)(Symbol.spliceOwner)

  '{
    val inputs: List[Dataset] = ${Expr.ofList(inputExprs)}
    val builtin = Builtin.Mpc(
      inputs,
      (ctx, ins) => Seq($expr(using ctx)) // note: the real implementation would also need to lift inputs
    )
    Dataset(builtin)
  }

}
