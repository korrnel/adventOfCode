package aoc2015

import org.jetbrains.kotlin.utils.addToStdlib.cast
import java.math.BigInteger
import java.security.MessageDigest

/*
--- Day 4: The Ideal Stocking Stuffer ---
Santa needs help mining some AdventCoins (very similar to bitcoins) to use as gifts for all the economically forward-thinking little girls and boys.

To do this, he needs to find MD5 hashes which, in hexadecimal, start with at least five zeroes. The input to the MD5 hash is some secret key (your puzzle input, given below) followed by a number in decimal. To mine AdventCoins, you must find Santa the lowest positive number (no leading zeroes: 1, 2, 3, ...) that produces such a hash.

For example:

If your secret key is abcdef, the answer is 609043, because the MD5 hash of abcdef609043 starts with five zeroes (000001dbbfa...), and it is the lowest such number to do so.
If your secret key is pqrstuv, the lowest number it combines with to make an MD5 hash starting with five zeroes is 1048970; that is, the MD5 hash of pqrstuv1048970 looks like 000006136ef....
Your puzzle answer was 254575.

--- Part Two ---
Now find one that starts with six zeroes.

Your puzzle answer was 1038736.

Both parts of this puzzle are complete! They provide two gold stars: **

* */
fun main(args: Array<String>) {

        val inputData = Common.getData(4,   "dev","2015")

        val inputData1 = "abcdef" // 609043
        val inputData2 = "pqrstuv" // 1048970

    println(Day04.Game_01(inputData1.trim(),false))
    println(Day04.Game_01(inputData2.trim(),false))
    println(Day04.Game_01(inputData.trim(),false))
    println(Day04.Game_02(inputData.trim(),false))

    }
class Day04 {
    companion object {
        fun md5(input:String): String {
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }
        fun Game_01(inputLines: String, debug : Boolean):Int {
           for (i in 0 until Int.MAX_VALUE)
            {
              if (md5(inputLines + i.toString()).substring(0,5).equals("00000")) return i
            }
        return 0
    }
        fun Game_02(inputLines: String, debug : Boolean):Int {
            for (i in 0 until Int.MAX_VALUE)
            {
                if (md5(inputLines + i.toString()).substring(0,6).equals("000000")) return i
            }
            return 0
        }
    }
}