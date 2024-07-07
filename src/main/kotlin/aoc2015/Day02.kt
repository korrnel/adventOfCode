package aoc2015

import org.jetbrains.kotlin.utils.addToStdlib.cast

fun main(args: Array<String>) {

        val inputData = Common.getData(2,   "dev","2015")

        val inputData1 = "2x3x4\n" +
                "1x1x10"

    println(Day02.Game_01(inputData1.trim().split("\n"),false))
    println(Day02.Game_01(inputData.trim().split("\n"),false))
    println(Day02.Game_02(inputData1.trim().split("\n"),false))
    println(Day02.Game_02(inputData.trim().split("\n"),false))

    }
class Day02 {
    companion object {
    fun Game_01(inputLines: List<String>, debug : Boolean):Long {
        // how to calculate
        // 2*l*w + 2*w*h + 2*h*l
        // area of the smallest
        var sum =0L
        inputLines.forEach {it ->
            sum+= calculatePaper(it.split("x"))
        }

        return sum
    }

        private fun calculatePaper(it: List<String>): Long {
            // 2*l*w + 2*w*h + 2*h*l + the smallest

            return 2*it[0].toLong()*it[1].toLong() +
                    2*it[1].toLong()*it[2].toLong() +
                   2*it[0].toLong()*it[2].toLong()+
                    Math.min(Math.min(it[0].toLong()*it[1].toLong(),
                    it[1].toLong()*it[2].toLong()),
                    it[0].toLong()*it[2].toLong())
        }

        fun Game_02(inputLines: List<String>, debug : Boolean):Long {
            // how to calculate ribbon
            // 2*l+2*w + l*w*h
            var sum =0L
            inputLines.forEach {it ->
                sum+= calculateRibbon(it.split("x").map { it.toLong() }.sorted())
            }

            return sum
        }
        private fun calculateRibbon(it: List<Long>): Long {
            // 2*l+2*w + l*w*h

            return 2*it[0].toLong()+ 2*it[1].toLong() +
                    it[0].toLong()*it[1].toLong()*it[2].toLong()
        }

    }
}