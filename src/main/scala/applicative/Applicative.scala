package applicative

import functor._

trait Applicative[F[_]] extends Functor[F] {
  def pure[A](a: A): F[A]
  def apply[A, B](f: F[A => B])(fa: F[A]): F[B]
  override def map[A, B](fa: F[A])(f: A => B): F[B] = apply(pure(f))(fa)
}

object Applicative {
  object OptionAp extends Applicative[Option] {
    def pure[A](a: A): Option[A] = Some(a)
    def apply[A, B](f: Option[A => B])(opt: Option[A]): Option[B] = (f, opt) match {
      case (Some(g), Some(a)) => Some(g(a))
      case _ => None
    }
  }

  object SeqAp extends Applicative[Seq] {
    def pure[A](a: A): Seq[A] = a +: Nil
    def apply[A, B](fs: Seq[A => B])(seq: Seq[A]): Seq[B] = for {
      f <- fs
      x <- seq
    } yield f(x)
  }
}

trait Apply[A, F[_]] {
  def <*>[B](f: F[A => B]): F[B]
}

object Apply {
  implicit class OptApO[A](opt: Option[A]) extends Apply[A, Option] {
    def <*>[B](f: Option[A => B]): Option[B] =
      Applicative.OptionAp.apply(f)(opt)
  }

  implicit class SeqApO[A](seq: Seq[A]) extends Apply[A, Seq] {
    def <*>[B](fs: Seq[A => B]): Seq[B] =
      Applicative.SeqAp.apply(fs)(seq)
  }
}
