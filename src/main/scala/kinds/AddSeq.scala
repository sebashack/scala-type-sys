package add.sequence

trait Add[T] {
  def add(t1: T, t2: T): T
}

object Add {
  implicit val addInt: Add[Int] = new Add[Int] {
    def add(i1: Int, i2: Int): Int = i1 + i2
  }

  implicit val addIntPair: Add[(Int, Int)] = new Add[(Int,Int)] {
    def add(p1: (Int,Int), p2: (Int,Int)): (Int,Int) =
      (p1._1 + p2._1, p1._2 + p2._2)
  }
}

object AddSeq {
  def sumSeq[T : Add](seq: Seq[T]): T =
    seq.reduce(implicitly[Add[T]].add(_,_))

  // def sumSeq[T](seq: Seq[T])(implicit obj: Add[T]): T =
  //   seq.reduce(obj.add(_,_))
}
