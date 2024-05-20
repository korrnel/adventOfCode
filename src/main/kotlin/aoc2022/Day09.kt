package aoc2022

import kotlin.math.min


fun  main(args: Array<String>) {
// rope movement
    val inputData = Common.getData(9, if (args.size > 0) args.get(0) else "dev", "2022")
    val inputData1 = "R 4\n" +
            "U 4\n" +
            "L 3\n" +
            "D 1\n" +
            "R 4\n" +
            "D 1\n" +
            "L 5\n" +
            "R 2\n" +
            ""

    // Map
    // ......
    // ......
    // ......
    // ......
    // H.....

    // result
    //
    // ..##..
    // ...##.
    // .####.
    // ....#.
    // s###..

// part 1  - how many squares the tail visited
    println( Day09.Game_01(inputData1.trim(), true))
    println( Day09.Game_01(inputData.trim(), false))



}
class Day09 {
    companion object {
        data class Node(val i: Int) {
        }

        fun Game_01(inputLines:String, debug: Boolean,): Int {
            var map = inputLines.split("\n").map { it.trim().split(" ") }
            // init, they are on the same spot
            var tail = Pair<Int,Int>(1,5)
            var head = tail

            var visited = mutableListOf<Pair<Int,Int>>()

            visited.add(tail)

            map.forEach { it ->
                    // do the steps
                    if (debug) println()
                    if (debug) println(it)
                    for (i in 0 until it[1].toInt()) {
                        // move
                        when (it[0]) {
                            "U" -> { head = Pair(head.first,head.second-1) }
                            "D" -> { head = Pair(head.first,head.second+1) }
                            "L" -> { head = Pair(head.first-1,head.second) }
                            "R" -> { head = Pair(head.first+1,head.second) }
                        }

                        if (!tail.equals(head)){
                            // diagonal fix
                            if (distance(head,tail)>1) when (it[0]) {
                                "U" -> { tail = Pair(head.first,Math.max(tail.second,head.second)) }
                                "D" -> { tail = Pair(head.first,Math.min(tail.second,head.second)) }
                                "L" -> { tail = Pair(Math.max(head.first,tail.first),head.second) }
                                "R" -> { tail = Pair(Math.min(head.first,tail.first),head.second) }
                            }
                            // 1 click away fix linear
                            when (it[0]) {
                                "U" -> { if (tail.second-head.second>1) tail = Pair(tail.first,tail.second-1) }
                                "D" -> { if (head.second-tail.second>1) tail = Pair(tail.first,tail.second+1) }
                                "L" -> { if (tail.first-head.first>1) tail = Pair(tail.first-1,tail.second) }
                                "R" -> { if (head.first-tail.first>1) tail = Pair(tail.first+1,tail.second) }
                            }

                        }
                        if (debug) print(tail)
                        visited.add(tail)
                    }
            }

            println(visited)
            return visited.distinct().count()

        }

        private fun distance(head: Pair<Int, Int>, tail: Pair<Int, Int>): Int {
            return Math.sqrt(Math.pow((head.first-tail.first).toDouble(), 2.0)+Math.pow((head.second-tail.second).toDouble(), 2.0)).toInt()
        }


    }
}