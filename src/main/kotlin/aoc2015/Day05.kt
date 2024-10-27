package aoc2015

import org.jetbrains.kotlin.util.collectionUtils.forEachScope
import org.jetbrains.kotlin.utils.addToStdlib.cast
import java.math.BigInteger
import java.security.MessageDigest

/*

--- Day 5: Doesn't He Have Intern-Elves For This? ---
Santa needs help figuring out which strings in his text file are naughty or nice.

A nice string is one with all of the following properties:

It contains at least three vowels (aeiou only), like aei, xazegov, or aeiouaeiouaeiou.
It contains at least one letter that appears twice in a row, like xx, abcdde (dd), or aabbccdd (aa, bb, cc, or dd).
It does not contain the strings ab, cd, pq, or xy, even if they are part of one of the other requirements.
For example:

ugknbfddgicrmopn is nice because it has at least three vowels (u...i...o...), a double letter (...dd...), and none of the disallowed substrings.
aaa is nice because it has at least three vowels and a double letter, even though the letters used by different rules overlap.
jchzalrnumimnmhp is naughty because it has no double letter.
haegwjzuvuyypxyu is naughty because it contains the string xy.
dvszwmarrgswjxmb is naughty because it contains only one vowel.
How many strings are nice?

Your puzzle answer was 236.

--- Part Two ---
Realizing the error of his ways, Santa has switched to a better model of determining whether a string is naughty or nice. None of the old rules apply, as they are all clearly ridiculous.

Now, a nice string is one with all of the following properties:

It contains a pair of any two letters that appears at least twice in the string without overlapping, like xyxy (xy) or aabcdefgaa (aa), but not like aaa (aa, but it overlaps).
It contains at least one letter which repeats with exactly one letter between them, like xyx, abcdefeghi (efe), or even aaa.
For example:

qjhvhtzxzqqjkmpb is nice because is has a pair that appears twice (qj) and a letter that repeats with exactly one letter between them (zxz).
xxyxx is nice because it has a pair that appears twice and a letter that repeats with one between, even though the letters used by each rule overlap.
uurcxstgmygtbstg is naughty because it has a pair (tg) but no repeat with a single letter between them.
ieodomkazucvgmuy is naughty because it has a repeating letter with one between (odo), but no pair that appears twice.
How many strings are nice under these new rules?

Your puzzle answer was 51.

Both parts of this puzzle are complete! They provide two gold stars: **

* */
fun main(args: Array<String>) {

        val inputData = Common.getData(5,   "dev","2015")

        val inputData1 = "ugknbfddgicrmopn\n" + // nice
                "aaa\n" + // nice
                "jchzalrnumimnmhp\n" + // naughty
                "haegwjzuvuyypxyu\n" +// naughty
                "dvszwmarrgswjxmb" // naughty
        val inputData2 = "qjhvhtzxzqqjkmpb\n" + // nice
                "xxyxx\n" + // nice
                "uurcxstgmygtbstg\n" +// naughty
                "ieodomkazucvgmuy" // naughty

    println(Day05.Game_01(inputData1.trim().split("\n"),false))
    println(Day05.Game_01(inputData.trim().split("\n"),false))
    println(Day05.Game_02(inputData2.trim().split("\n"),true))
    println(Day05.Game_02(inputData.trim().split("\n"),false))

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
            var nice =0
            inputLines.forEach {
                if (debug) print(it+" ")
                if (nice21(it) && nice22(it)) {
                    nice++
                    if (debug) println("nice")
                }  else if (debug)  println("naughty")

            }

            return nice
        }

        fun nice21(input:String): Boolean {
            // pairs
            // It contains a pair of any two letters that appears at least twice in the string without overlapping,
            // like xyxy (xy) or aabcdefgaa (aa), but not like aaa (aa, but it overlaps).
            for (i in 1 until input.length-1) {
                if (input.substring(i+1).contains(input.substring(i-1,i+1))) return true
            }
            return false
        }
        fun nice22(input:String): Boolean {
            // repetion
            // It contains at least one letter which repeats with exactly one letter between them, like xyx, abcdefeghi (efe), or even aaa.
            for (i in 0 until input.length-2) {
                if (input[i].equals(input[i+2])) return true
            }
            return false
        }

    }
}