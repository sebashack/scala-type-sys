import button.click.observer._
import button.subject.observer.ButtonSubjectObserver._
import add.sequence.{ AddSeq }
import add.reduce.{ AddReduce }
import add.high.kind.{ AddHighReduce }
import sum.types.{ Breed }
import functor.{ Functor }
import functor.FunctorO._
import applicative.Applicative.OptionAp._
import applicative.Apply._
import monad.Monad.SeqM.{ bind }
import monad.MonadO._
import typeFamilies._

object Main extends App {
  val buttons = Vector(new ObservableButton("one"), new ObservableButton("two"))
  val observer = new ButtonClickObserver
  buttons.foreach(_.addObserver(observer))

  for (i <- 0 to 2) buttons(0).click()
  for (i <- 0 to 2) buttons(1).click()

  println("***************************")
  val mvF1: Move[Fire] = Move("Fire Blast")
  val mvF2: Move[Fire] = Move("Fire Canon")

  val mvW1: Move[Water] = Move("Water Bubbles")
  val mvW2: Move[Water] = Move("Water Beam")

  println(new FirePokemon(Array(mvF1, mvF2)).pickMove(charmander))
  println(new WaterPokemon(Array(mvW1, mvW2)).pickMove(squirtle))
  println("***************************")

  // val r1 = AddSeq.sumSeq(Option(2)) This won't compile
  // val r2 = AddReduce.sum[Int,Option](None) This won't compile

  println(AddSeq.sumSeq(Vector(1, 2, 3, 4, 5)))
  println("***************************")
  println(AddSeq.sumSeq(Seq(1, 2, 3, 4, 5)))
  println("***************************")
  println(AddReduce.sum(Vector(1, 2, 3, 4, 5)))
  println("***************************")
  println(AddReduce.sum(Seq(1, 2, 3, 4, 5)))
  println("***************************")
  println(AddReduce.sum(Some(34)))
  println("***************************")
  println(AddHighReduce.sum(Vector(1, 2, 3, 4, 5)))
  println("***************************")
  println(AddHighReduce.sum(Seq(1, 2, 3, 4, 5)))
  println("***************************")
  println(AddHighReduce.sum(Some(34)))
  println("***************************")
  for (breed <- Breed.values) println(s"${breed.id}\t$breed")
  println("***************************")
  println("Functor Examples")
  println("***************************")
  println(Functor.SeqF.map(List(1, 2, 3, 4))((n: Int) => n * 10))
  println(Seq(1, 2, 3, 4).fmap((n: Int) => n * 10))
  println("***************************")
  println(Functor.OptionF.map(Some(10))((n: Int) => n * 10))
  println(Some(10).fmap((n: Int) => n * 10))
  println(Functor.OptionF.map(None)((n: Int) => n * 10))
  println(None.fmap((n: Int) => n * 10))
  println("***************************")
  println(Functor.MapF.map(Map("A" -> 1, "B" -> 2, "C" -> 3, "D" -> 4))((n: Int) => n * 10))
  println(Map("A" -> 1, "B" -> 2, "C" -> 3, "D" -> 4).fmap((n: Int) => n * 10))
  println("***************************")
  println(Functor.FunctionF.map((n: Int) => n.toString)((s: String) => s + " is an Int")(123123))
  println("***************************")
  println("Applicative Examples")
  println("***************************")
  val m: Option[Int] = pure(1)
  val k: Option[Int] = pure(2)
  def g(n: Int)(m: Int): Int = (n + m) * 50
  println(m <*> (k <*> pure(g _)))
  println("***************************")
  val v: Vector[Int] = Vector(10, 20, 10, 20)
  val vfs: Vector[Int => Int] = Vector((n: Int) => n * 10, (n: Int) => n + 10)
  println(v <*> vfs)
  println("***************************")
  println("Monad Examples")
  println("***************************")
  val l1: List[Int] = List(1, 0, 1, 0)
  def lf(l: Int): List[Int] = List(l, l, l)
  println(bind(l1)(lf))

  val by2: Int => Option[Int] = (n: Int) => Some(2 * n)
  println(Some(22) >>= by2)
}
