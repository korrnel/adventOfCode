package aoc2015

import org.jetbrains.kotlin.js.translate.utils.splitToRanges

fun main(args: Array<String>) {

        val inputData = Common.getData(1,   "dev","2015")

        val inputData1 = "((("
        val inputData2 = "()())"

    println(Day01.Game_01(inputData1.trim(),false))
    println(Day01.Game_01(inputData.trim(),false))
    println(Day01.Game_02(inputData2.trim(),false))
    println(Day01.Game_02(inputData.trim(),false))
    }
class Day01 {
    companion object {
    fun Game_01(inputLines: String, debug : Boolean):Long {
        var floor = 0L
        // find the floor santa goes
        inputLines.forEach {it ->
            // sum them up
            when (it) {
                '(' -> floor++
                ')' -> floor--

            }

            if (debug) {
                println(floor)
            }
        }

        return floor
    }
    fun Game_02(inputLines: String, debug : Boolean):Long {
            var floor = 0L
            // find where santa enters te basement -1
            inputLines.forEachIndexed {index,it ->
                // sum them up
                when (it) {
                    '(' -> floor++
                    ')' -> floor--

                }
                if (floor<0) return index+1L // return it
                if (debug) {
                    println(floor)
                }
            }

            return floor
        }

    }
}