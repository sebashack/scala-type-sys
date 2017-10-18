package functor

// Approach 1: Passing object as a value.
trait Functor[F[_]] {
  def map[A, B](fa: F[A])(f: A => B): F[B]
}

object Functor {
  object SeqF extends Functor[Seq] {
    def map[A, B](seq: Seq[A])(f: A => B): Seq[B] = seq match {
      case Nil => Nil
      case (x +: xs) => f(x) +: map(xs)(f)
    }
  }

  object OptionF extends Functor[Option]{
    def map[A, B](opt: Option[A])(f: A => B): Option[B] = opt match {
      case None => None
      case Some(v) => Some(f(v))
    }
  }
}


// Approach 2: Wrapping object in implicit class which implements Functor
trait FunctorO[A, +M[_]] {
  def fmap[B](f: A => B): M[B]
}

object FunctorO {
  implicit class SeqFuncO[A](seq: Seq[A]) extends FunctorO[A, Seq] {
    def fmap[B](f: A => B): Seq[B] = Functor.SeqF.map(seq)(f)
  }

  implicit class OptFuncO[A](opt: Option[A]) extends FunctorO[A, Option] {
    def fmap[B](f: A => B): Option[B] = Functor.OptionF.map(opt)(f)
  }
}
