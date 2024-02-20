package aoc2022

import org.jetbrains.kotlin.backend.common.lower.rangeContainsLoweringPhase

fun  main(args: Array<String>) {
// rock paper scissor
    val inputData = Common.getData(4, if (args.size > 0) args.get(0) else "dev", "2022")
    val inputData1 = "2-4,6-8\n" +
            "2-3,4-5\n" +
            "5-7,7-9\n" +
            "2-8,3-7\n" +
            "6-6,4-6\n" +
            "2-6,4-8"

    println( Day04.Game_01(inputData1.split("\n"), false))
    println( Day04.Game_01(inputData.trim().split("\n"), false))
    println( Day04.Game_02(inputData1.split("\n"), true))
    println( Day04.Game_02(inputData.trim().split("\n"), false))

}
class Day04 {
    companion object {

        fun fullyContains(left: String,right: String): Boolean {
            var (startL, endL) = left.split("-").map { it.toInt() }
            var (startR, endR) = right.split("-").map { it.toInt() }
            return (startL<= startR && endR<=endL)

        }
        fun overlap(left: String,right: String): Boolean {
            var (startL, endL) = left.split("-").map { it.toInt() }
            var (startR, endR) = right.split("-").map { it.toInt() }
                // (StartA <= EndB) and (EndA >= StartB)
            return (startL<= endR && startR<=endL)

        }

        fun Game_02(inputLines: List<String>, debug: Boolean): Int {
            var sum = 0

            inputLines.forEach { it ->
                // commonChar(it.substring(0,it.length/2))
                val (left, right)  = it.split(",")

                if (overlap(left,right) || overlap(right,left)) {
                    if (debug) println(it)
                    sum++
                }

                }
            return sum
        }
        fun Game_01(inputLines: List<String>, debug: Boolean): Int {
            var sum = 0

            inputLines.forEach { it ->
                val (left, right)  = it.split(",")
                if (fullyContains(left,right) || fullyContains(right,left)) {
                    if (debug) println(it)
                    sum++
                }

            }
            return sum
        }
    }
}