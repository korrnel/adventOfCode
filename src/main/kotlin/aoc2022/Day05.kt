package aoc2022

import org.jetbrains.kotlin.backend.common.lower.rangeContainsLoweringPhase
import org.jetbrains.kotlin.cli.jvm.compiler.pipeline.createSession
import org.jetbrains.kotlin.ir.util.SymbolRemapper

fun  main(args: Array<String>) {
// rock paper scissor
    val inputData = Common.getData(5, if (args.size > 0) args.get(0) else "dev", "2022")
    val inputData1 = "    [D]    \n" +
            "[N] [C]    \n" +
            "[Z] [M] [P]\n" +
            " 1   2   3 \n" +
            "\n" +
            "move 1 from 2 to 1\n" +
            "move 3 from 1 to 3\n" +
            "move 2 from 2 to 1\n" +
            "move 1 from 1 to 2"

    println( Day05.Game_01(inputData1, false))
    println( Day05.Game_01(inputData, false))

    println( Day05.Game_02(inputData1, false))
    println( Day05.Game_02(inputData, false))

}
class Day05 {
    companion object {
        fun getCrates(cratesInput2: List<String>): MutableList<MutableList<Char>> {
            var crates = mutableListOf(mutableListOf<Char>())

            var cratesInput = cratesInput2.slice(0..cratesInput2.size-2)
            // parse crates
            cratesInput.forEach {
                var stack=0
                for (i in 1..it.length step (4)) {
                    if (crates[stack].isNullOrEmpty()) crates.add(mutableListOf<Char>())
                    if (it[i]!=' ') crates[stack].add(it[i])
                    stack++
                }
            }
            return crates
        }
        fun Game_01(inputLines: String, debug: Boolean): String {
            var sum = ""
            // var crates = mutableListOf(mutableListOf<Char>())
            var crates = getCrates(inputLines.split("\n\n").get(0).split("\n"))

            // get the steps
            var movingInput= inputLines.split("\n\n").get(1).trim().split("\n")
            val regex= "move\\s(\\d+)\\sfrom\\s(\\d+)\\sto\\s(\\d+)".toRegex()

            movingInput.forEach {
                val matchResult = regex.find(it)!!
                val (count,from, to) = matchResult.destructured
                if (debug) println(count + " " + from + " " + to )
                if (debug) println(crates)
                // move it one by one
                for (i in 1 .. count.toInt()) {
                    try {
                        if (debug) println(count + " " + from + " " + to )
                        if (debug) println(crates)
                        crates[to.toInt()-1].addFirst(crates[from.toInt()-1].removeFirst())
                    } catch (e:NoSuchElementException) {
                       // the input was trimmed , and it messed up
                        println(count + " " + from + " " + to )
                        println(crates)
                    }
                }
            }
            crates.forEach {
                try {
                    sum += it.first()
                } catch (e:NoSuchElementException) {
                    sum += " "
                }
            }
            return sum
        }
        fun Game_02(inputLines: String, debug: Boolean): String {
            var sum = ""
            // var crates = mutableListOf(mutableListOf<Char>())
            var crates = getCrates(inputLines.split("\n\n").get(0).split("\n"))

            // get the steps
            var movingInput= inputLines.split("\n\n").get(1).trim().split("\n")
            val regex= "move\\s(\\d+)\\sfrom\\s(\\d+)\\sto\\s(\\d+)".toRegex()

            movingInput.forEach {
                val matchResult = regex.find(it)!!
                val (count,from, to) = matchResult.destructured
                // move it once
                for (i in (1 .. count.toInt()).reversed()) {
                    crates[to.toInt()-1].addFirst(crates[from.toInt()-1].removeAt(i-1))
                }
            }
            crates.forEach {
                try {
                    sum += it.first()
                } catch (e:NoSuchElementException) {
                    sum += " "
                }
            }
            return sum
        }

    }
}