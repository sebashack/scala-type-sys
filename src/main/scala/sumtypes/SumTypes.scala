package sum.types

object Breed extends Enumeration {
  type Breed = Value
  val doberman = Value("Doberman Pinscher")
  val yorkie = Value("Yorkshire Terrier")
  val scottie = Value("Scottish Terrier")
  val dane = Value("Great Dane")
  val portie = Value("Portuguese Water Dog")
}

sealed trait Breed2 {
  val name: String
  val age: Int
}

case object doberman extends Breed2 {
  val name = "Doberman Pinscher"
  val age = 2
}
case object yorkie extends Breed2 {
  val name = "Yorkshire Terrier"
  val age = 2
}
case object scottie extends Breed2 {
  val name = "Scottish Terrie"
  val age = 2
}
case object dane extends Breed2 {
  val name = "Great Dane"
  val age = 2
}
case object portie extends Breed2 {
  val name = "Portuguese Water Dog"
  val age = 2
}
