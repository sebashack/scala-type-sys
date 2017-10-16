package add.reduce

import add.sequence._

trait Reduce[T, -M[T]] {
  def reduce(m: M[T])(f: (T, T) => T): T
}

object Reduce {
  implicit def seqReduce[T]: Reduce[T, Seq] = new Reduce[T, Seq] {
    def reduce(seq: Seq[T])(f: (T, T) => T): T = seq reduce f
  }

  implicit def optionReduce[T]: Reduce[T, Option] = new Reduce[T, Option] {
    def reduce(opt: Option[T])(f: (T, T) => T): T = opt.reduce(f)
  }
}

object AddReduce {
  def sum[T : Add, M[T]](container: M[T])(implicit red: Reduce[T,M]): T =
    red.reduce(container)(implicitly[Add[T]].add(_,_))
}
