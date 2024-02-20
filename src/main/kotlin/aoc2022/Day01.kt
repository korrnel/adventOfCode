package aoc2022

import org.jetbrains.kotlin.js.translate.utils.splitToRanges

fun main(args: Array<String>) {

        val inputData = Common.getData(1,  if (args.size>0) args.get(0) else "dev","2022")

        val inputData1 = "1000\n" +
                "2000\n" +
                "3000\n" +
                "\n" +
                "4000\n" +
                "\n" +
                "5000\n" +
                "6000\n" +
                "\n" +
                "7000\n" +
                "8000\n" +
                "9000\n" +
                "\n" +
                "10000"

    println(Day01.Game_01(inputData1.split("\n\n"),false))
    println(Day01.Game_01(inputData.split("\n\n"),false))
    println(Day01.Game_02(inputData1.split("\n\n"),false))
    println(Day01.Game_02(inputData.split("\n\n"),false))
    }
class Day01 {
    companion object {
    fun Game_01(inputLines: List<String>, debug : Boolean):Long {
        var max = 0L
        // find the first and last numbers in the line
        inputLines.forEach {it ->
            // sum them up
            var deer = it.trim().split("\n").map { it.trim().toLong() }
            if (deer.sum()>max) max=deer.sum()
            if (debug) {
                println(max)
            }
        }

        return max
    }
        fun Game_02(inputLines: List<String>, debug : Boolean):Long {
            var max = mutableListOf<Long>()
            // find the first and last numbers in the line
            inputLines.forEach {it ->
                // sum them up
                var deer = it.trim().split("\n").map { it.trim().toLong() }
                max.add(deer.sum())
                if (debug) {
                    println(max)
                }
            }
            if (debug) {
                max.sortedDescending().slice(0..2)
            }

            return max.sortedDescending().slice(0..2).sum()
        }

    }
}