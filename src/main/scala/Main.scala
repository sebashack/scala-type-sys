import button.click.observer._
import button.subject.observer.ButtonSubjectObserver._
import add.sequence.{ AddSeq }
import add.reduce.{ AddReduce }
import add.high.kind.{ AddHighReduce }

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

}
