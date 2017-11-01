package typeFamilies

sealed trait Fire { val name: String }
case object charmander extends Fire { val name = "Charmander" }
case object charmelion extends Fire { val name = "Charmelion" }

sealed trait Water { val name: String }
case object squirtle extends Water { val name = "Squirtle" }
case object warturtle extends Fire { val name = "Warturtle" }


case class Move[PokeType](attack: String)

abstract class Pokemon {
  type PokeType
  def pickMove(pkType: PokeType): Move[PokeType]
}


class FirePokemon(attacks: Array[Move[Fire]]) extends Pokemon {
  type PokeType = Fire

  val r = scala.util.Random
  def pickMove(pkType: Fire): Move[Fire] = attacks(r.nextInt(attacks.length))
}

class WaterPokemon(attacks: Array[Move[Water]]) extends Pokemon {
  type PokeType = Water

  val r = scala.util.Random
  def pickMove(pkType: Water): Move[Water] = attacks(r.nextInt(attacks.length))
}
