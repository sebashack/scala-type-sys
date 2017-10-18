import button.click.observer._
import button.subject.observer.ButtonSubjectObserver._
import add.sequence.{ AddSeq }
import add.reduce.{ AddReduce }
import add.high.kind.{ AddHighReduce }
import sum.types.{ Breed }
import functor.{ Functor }
import functor.FunctorO._


object Main extends App {
  val buttons = Vector(new ObservableButton("one"), new ObservableButton("two"))
  val observer = new ButtonClickObserver
  buttons.foreach(_.addObserver(observer))

  for (i <- 0 to 2) buttons(0).click()
  for (i <- 0 to 2) buttons(1).click()

  // val r1 = AddSeq.sumSeq(Option(2)) This won't compile
  // val r2 = AddReduce.sum[Int,Option](None) This won't compile

  println("***************************")
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
  println(Functor.SeqF.map(List(1, 2, 3, 4))((n: Int) => n * 10))
  println(List(1, 2, 3, 4).fmap((n: Int) => n * 10))
  println("***************************")
  println(Functor.OptionF.map(Some(10))((n: Int) => n * 10))
  println(Some(10).fmap((n: Int) => n * 10))
  println(Functor.OptionF.map(None)((n: Int) => n * 10))
  println(None.fmap((n: Int) => n * 10))
}
