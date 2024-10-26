package aoc2015

import org.jetbrains.kotlin.utils.addToStdlib.cast
/*
--- Day 3: Perfectly Spherical Houses in a Vacuum ---
Santa is delivering presents to an infinite two-dimensional grid of houses.

He begins by delivering a present to the house at his starting location, and then an elf at the North Pole calls him via radio and tells him where to move next. Moves are always exactly one house to the north (^), south (v), east (>), or west (<). After each move, he delivers another present to the house at his new location.

However, the elf back at the north pole has had a little too much eggnog, and so his directions are a little off, and Santa ends up visiting some houses more than once. How many houses receive at least one present?

For example:

> delivers presents to 2 houses: one at the starting location, and one to the east.
^>v< delivers presents to 4 houses in a square, including twice to the house at his starting/ending location.
^v^v^v^v^v delivers a bunch of presents to some very lucky children at only 2 houses.
Your puzzle answer was 2565.

The first half of this puzzle is complete! It provides one gold star: *

--- Part Two ---
The next year, to speed up the process, Santa creates a robot version of himself, Robo-Santa, to deliver presents with him.

Santa and Robo-Santa start at the same location (delivering two presents to the same starting house), then take turns moving based on instructions from the elf, who is eggnoggedly reading from the same script as the previous year.

This year, how many houses receive at least one present?

For example:

^v delivers presents to 3 houses, because Santa goes north, and then Robo-Santa goes south.
^>v< now delivers presents to 3 houses, and Santa and Robo-Santa end up back where they started.
^v^v^v^v^v now delivers presents to 11 houses, with Santa going one direction and Robo-Santa going the other.
Your puzzle answer was 2639.

Both parts of this puzzle are complete! They provide two gold stars: **


* */
fun main(args: Array<String>) {

        val inputData = Common.getData(3,   "dev","2015")

        val inputData1 = "^v^v^v^v^v"
        val inputData2 = "^>v<"

    println(Day03.Game_01(inputData1.trim(),false))
    println(Day03.Game_01(inputData.trim(),false))
    println(Day03.Game_02(inputData1.trim(),false))
    println(Day03.Game_02(inputData2.trim(),false))
    println(Day03.Game_02(inputData.trim(),false))

    }
class Day03 {
    companion object {
    fun Game_01(inputLines: String, debug : Boolean):Int {
        var visitedPlaces = mutableMapOf<Pair<Int,Int>,Int>()
        var positionX = 0
        var positionY = 0
        visitedPlaces.put(Pair(positionX,positionY), visitedPlaces.get(Pair(positionX,positionY))?.plus(1) ?:1)

        inputLines.forEach {
            when (it) {
            '<' -> positionX--
            '>' -> positionX++
            '^' -> positionY--
            'v' -> positionY++
            }

            visitedPlaces.put(Pair(positionX,positionY), visitedPlaces.get(Pair(positionX,positionY))?.plus(1) ?:1)

        }

        return visitedPlaces.size
    }

     fun Game_02(inputLines: String, debug : Boolean):Int {

          var visitedPlaces = mutableMapOf<Pair<Int,Int>,Int>()
          var visitedPlaces2 = mutableMapOf<Pair<Int,Int>,Int>()
          var positionX = 0
          var positionY = 0
          var position2X = 0
          var position2Y = 0
         visitedPlaces.put(Pair(positionX,positionY), visitedPlaces.get(Pair(positionX,positionY))?.plus(1) ?:1)

         for (i in 0 until inputLines.length step 2 ) {

                when (inputLines.get(i)) {
                    '<' -> positionX--
                    '>' -> positionX++
                    '^' -> positionY--
                    'v' -> positionY++
                }
                visitedPlaces.put(Pair(positionX,positionY), visitedPlaces.get(Pair(positionX,positionY))?.plus(1) ?:1)

                when (inputLines.get(i+1)) {
                   '<' -> position2X--
                   '>' -> position2X++
                   '^' -> position2Y--
                   'v' -> position2Y++
                }
                visitedPlaces.put(Pair(position2X,position2Y), visitedPlaces.get(Pair(position2X,position2Y))?.plus(1) ?:1)
            }
            return visitedPlaces.size
        }

    }
}