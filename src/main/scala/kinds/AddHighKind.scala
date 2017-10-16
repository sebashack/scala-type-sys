package add.high.kind

import add.sequence._

trait Reduce[-M[_]] {
  def reduce[T](m: M[T])(f: (T, T) => T): T
}

object Reduce {
  implicit val seqReduce: Reduce[Seq] = new Reduce[Seq] {
    def reduce[T](seq: Seq[T])(f: (T, T) => T): T = seq reduce f
  }

  implicit val optionReduce: Reduce[Option] = new Reduce[Option] {
    def reduce[T](opt: Option[T])(f: (T, T) => T): T = opt reduce f
  }
}

object AddHighReduce {
  def sum[T: Add, M[_]: Reduce](container: M[T]): T =
    implicitly[Reduce[M]].reduce(container)(implicitly[Add[T]].add(_,_))
}
