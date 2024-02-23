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

}
class Day05 {
    companion object {
        fun Game_01(inputLines: String, debug: Boolean): String {
            var sum = ""
            var crates = mutableListOf(mutableListOf<Char>())
            var cratesInput= inputLines.split("\n\n").get(0).split("\n")
            cratesInput = cratesInput.slice(0..cratesInput.size-2)
            if (!debug) println(cratesInput)
            // parse crates
            cratesInput.forEach {
                var stack=0
                if (debug) println(it)
                for (i in 1..it.length step (4)) {
                    if (crates[stack].isNullOrEmpty()) crates.add(mutableListOf<Char>())
                    if (it[i]!=' ') crates[stack].add(it[i])
                    stack++
                }
            }
            if (debug) println(crates)

            // get the steps
            var movingInput= inputLines.split("\n\n").get(1).trim().split("\n")
            val regex= "move\\s(\\d+)\\sfrom\\s(\\d+)\\sto\\s(\\d+)".toRegex()

            movingInput.forEach {
                val matchResult = regex.find(it)!!
                val (count,from, to) = matchResult.destructured
                if (debug) println(count + " " + from + " " + to )
                if (debug) println(crates)

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
    }
}