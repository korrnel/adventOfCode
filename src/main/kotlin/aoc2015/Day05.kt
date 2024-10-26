package aoc2015

import org.jetbrains.kotlin.util.collectionUtils.forEachScope
import org.jetbrains.kotlin.utils.addToStdlib.cast
import java.math.BigInteger
import java.security.MessageDigest

/*


* */
fun main(args: Array<String>) {

        val inputData = Common.getData(5,   "dev","2015")

        val inputData1 = "ugknbfddgicrmopn\n" + // nice
                "aaa\n" + // nice
                "jchzalrnumimnmhp\n" + // naughty
                "haegwjzuvuyypxyu\n" +// naughty
                "dvszwmarrgswjxmb" // naughty

    println(Day05.Game_01(inputData1.trim().split("\n"),false))
    println(Day05.Game_01(inputData.trim().split("\n"),false))

    //  println(Day05.Game_02(inputData.trim().split("\n"),false))

    }
class Day05 {
    companion object {
        fun nice1(input:String): Boolean {
            // aeiou
            var counter = 0
           input.forEach {
               if ("aeiou".contains(it)) counter++
           }
            return counter>=3
        }
        fun nice2(input:String): Boolean {
            // double
            for (i in 1 until input.length) {
                if (input[i-1].equals(input[i])) return true
            }
            return false
        }
        fun nice3(input:String): Boolean {
            // ab, cd, pq, xy
            return  !(input.contains("ab")||input.contains("cd")||input.contains("pq")||input.contains("xy"))

        }
        fun Game_01(inputLines: List<String>, debug : Boolean):Int {
            var nice =0
            inputLines.forEach {
                if (nice1(it) && nice2(it)&& nice3(it)) nice++ // println("nice")
                 // else println("naughty")
            }

            return nice
    }
        fun Game_02(inputLines: List<String>, debug : Boolean):Int {
            return 0
        }
    }
}