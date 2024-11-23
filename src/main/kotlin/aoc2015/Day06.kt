package aoc2015

import aoc2022.Game01
import org.jetbrains.kotlin.config.AnalysisFlag
import org.jetbrains.kotlin.util.collectionUtils.forEachScope
import org.jetbrains.kotlin.utils.addToStdlib.cast
import java.math.BigInteger
import java.security.MessageDigest
import kotlin.math.max

/*
--- Day 6: Probably a Fire Hazard ---
Because your neighbors keep defeating you in the holiday house decorating contest year after year, you've decided to deploy one million lights in a 1000x1000 grid.

Furthermore, because you've been especially nice this year, Santa has mailed you instructions on how to display the ideal lighting configuration.

Lights in your grid are numbered from 0 to 999 in each direction; the lights at each corner are at 0,0, 0,999, 999,999, and 999,0. The instructions include whether to turn on, turn off, or toggle various inclusive ranges given as coordinate pairs. Each coordinate pair represents opposite corners of a rectangle, inclusive; a coordinate pair like 0,0 through 2,2 therefore refers to 9 lights in a 3x3 square. The lights all start turned off.

To defeat your neighbors this year, all you have to do is set up your lights by doing the instructions Santa sent you in order.

For example:

turn on 0,0 through 999,999 would turn on (or leave on) every light.
toggle 0,0 through 999,0 would toggle the first line of 1000 lights, turning off the ones that were on, and turning on the ones that were off.
turn off 499,499 through 500,500 would turn off (or leave off) the middle four lights.
After following the instructions, how many lights are lit?

Your puzzle answer was 377891.

--- Part Two ---
You just finish implementing your winning light pattern when you realize you mistranslated Santa's message from Ancient Nordic Elvish.

The light grid you bought actually has individual brightness controls; each light can have a brightness of zero or more. The lights all start at zero.

The phrase turn on actually means that you should increase the brightness of those lights by 1.

The phrase turn off actually means that you should decrease the brightness of those lights by 1, to a minimum of zero.

The phrase toggle actually means that you should increase the brightness of those lights by 2.

What is the total brightness of all lights combined after following Santa's instructions?

For example:

turn on 0,0 through 0,0 would increase the total brightness by 1.
toggle 0,0 through 999,999 would increase the total brightness by 2000000.
Your puzzle answer was 14110788.

Both parts of this puzzle are complete! They provide two gold stars: **
* */
fun main(args: Array<String>) {

        val inputData = Common.getData(6,   "dev","2015")

        val inputData1 = "turn on 0,0 through 0,0\n" +
                "toggle 0,0 through 999,999"

    println(Day06.Game_01(inputData1.trim().split("\n"),false))
//    println(Day06.Game_01(inputData.trim().split("\n"),false))

    println(Day06.Game_02(inputData1.trim().split("\n"),false))
    println(Day06.Game_02(inputData.trim().split("\n"),false))

    //  println(Day05.Game_02(inputData.trim().split("\n"),false))

    }
class Day06 {
    companion object {

        fun Game_01(inputLines: List<String>, debug : Boolean):Int {
            var lights  = mutableMapOf<Pair<Int,Int>,Boolean>()
            var nice =0
            inputLines.forEach {

                var (toogleOrnot,x1, y1,x2,y2) = "(toggle|on|off)\\s(\\d*),(\\d*)\\s\\w+\\s(\\d*),(\\d*)".toRegex().find(it)!!.destructured
               // println(toogleOrnot + x1+ y1 + x2 + y2)
                for (x in x1.toInt() .. x2.toInt()) {
                    for (y in y1.toInt() .. y2.toInt()) {
                        if (toogleOrnot=="on") lights.put(Pair(x,y),true)
                        if (toogleOrnot=="off") lights.put(Pair(x,y),false)
                        if (toogleOrnot=="toggle") {
                            lights.put(Pair(x,y),
                                !(lights.get(Pair(x,y))?:false)
                            )
                        }

                    }
                }
            }
            var sum=0
            lights.forEach{
                if (it.value) sum++
            }
            return sum
        }
        fun Game_02(inputLines: List<String>, debug : Boolean):Int {
            var lights  = mutableMapOf<Pair<Int,Int>,Int>()
            inputLines.forEach {

                var (toogleOrnot,x1, y1,x2,y2) = "(toggle|on|off)\\s(\\d*),(\\d*)\\s\\w+\\s(\\d*),(\\d*)".toRegex().find(it)!!.destructured
                // println(toogleOrnot + x1+ y1 + x2 + y2)
                for (x in x1.toInt() .. x2.toInt()) {
                    for (y in y1.toInt() .. y2.toInt()) {
                        if (toogleOrnot=="on") lights.put(Pair(x,y),  (lights.get(Pair(x,y))?:0)+1)
                        if (toogleOrnot=="off") {
                            lights.put(Pair(x,y),  max(0, (lights.get(Pair(x,y))?:0)-1 ))
                        }
                        if (toogleOrnot=="toggle") {
                            lights.put(Pair(x,y),
                                (lights.get(Pair(x,y))?:0)+2
                            )
                        }

                    }
                }
            }
            var sum=0
            lights.forEach{
                 sum += it.value
            }
            return sum
        }

    }
}