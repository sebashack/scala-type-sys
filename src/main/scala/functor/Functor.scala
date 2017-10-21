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

  object MapF {
    def map[K, V1, V2](mapKV1: Map[K, V1])(f: V1 => V2): Map[K, V2] = {
      val functor = new Functor[({type L[a] = Map[K, a]})#L] {
        def map[A, B](mapKV1: Map[K, A])(g: A => B): Map[K, B] = {
          val l = SeqF.map(mapKV1.toList)({ case (k, v) => (k, g(v)) })
          l.toMap
        }
      }

      functor.map(mapKV1)(f)
    }
  }

  object FunctionF {
    def map[A,A2,B](func: A => A2)(f: A2 => B): A => B = {
      val functor = new Functor[({type λ[β] = A => β})#λ] {
        def map[C,D](func: A => C)(f: C => D): A => D = (a: A) => f(func(a))
      }
      functor.map(func)(f)
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

  implicit class MapFuncO[K,V1](mapKV1: Map[K,V1])
      extends FunctorO[V1, ({type L[a] = Map[K,a]})#L] {
    def fmap[V2](f: V1 => V2): Map[K,V2] = Functor.MapF.map(mapKV1)(f)
  }
}
