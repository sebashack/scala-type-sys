package monad

import applicative._

trait Monad[M[_]] extends Applicative[M] {
  def bind[A, B](ma: M[A])(f: A => M[B]): M[B]

  override def apply[A, B](f: M[A => B])(ma: M[A]): M[B] =
    bind(f)((g: A => B) => bind(ma)((a: A) => pure(g(a))))
}

object Monad {
  object OptionM extends Monad[Option] {
    def bind[A, B](opt: Option[A])(f: A => Option[B]): Option[B] = opt match {
      case Some(a) => f(a)
      case _ => None
    }

    def pure[A](a: A): Option[A] =
      Applicative.OptionAp.pure(a)
  }

  object SeqM extends Monad[Seq] {
    def bind[A, B](ma: Seq[A])(f: A => Seq[B]): Seq[B] = for {
      mb <- ma.map(f)
      b <- mb
    } yield b

    def pure[A](a: A): Seq[A] = Applicative.SeqAp.pure(a)
  }
}


trait MonadO[A, M[_]] {
  def >>=[B](f: A => M[B]): M[B]
}

object MonadO {
  implicit class OptionMO[A](opt: Option[A]) extends MonadO[A, Option] {
    def >>=[B](f: A => Option[B]): Option[B] = Monad.OptionM.bind(opt)(f)
  }

  implicit class SeqMO[A](opt: Seq[A]) extends MonadO[A, Seq] {
    def >>=[B](f: A => Seq[B]): Seq[B] = Monad.SeqM.bind(opt)(f)
  }
}
