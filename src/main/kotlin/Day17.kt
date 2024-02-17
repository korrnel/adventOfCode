import java.util.*

fun main(args: Array<String>) {

    val inputData = Common.getData(17, args[0])


    var inputData1 =
            "2413432311323\n" +
            "3215453535623\n" +
            "3255245654254\n" +
            "3446585845452\n" +
            "4546657867536\n" +
            "1438598798454\n" +
            "4457876987766\n" +
            "3637877979653\n" +
            "4654967986887\n" +
            "4564679986453\n" +
            "1224686865563\n" +
            "2546548887735\n" +
            "4322674655533"
        println( Day17.Game_01(inputData.trim().split("\n"),1,3,false))
        println( Day17.Game_01(inputData.trim().split("\n"),4,10,false))

}

class Day17 {
    data class Point(val x: Int, val y: Int)
    companion object {
        var way = mutableListOf<MutableList<Int>>()

        data class State(val cost: Int, val x: Int, val y: Int, val dd: Int)

        val dirs = listOf(Pair(0, 1), Pair(1, 0), Pair(0, -1), Pair(-1, 0))


        fun valid(row : Int, col : Int , maxR : Int, maxCol :  Int) : Boolean {
            if ( row>-1 && row<maxR && col>-1 &&col < maxCol) return  true
            return  false
        }
        fun Game_01(inputLines: List<String>,mindist: Int, maxdist: Int,  debug: Boolean) : Int {
            val map = inputLines.map { line -> line.map { it.digitToInt() } }

            // init

            println(map)
            val q = PriorityQueue<State>(compareBy { it.cost })
            q.offer(State(0, 0, 0, -1))
            val costs = mutableMapOf<Triple<Int, Int, Int>, Int>()
// GO
            while (q.isNotEmpty()) {
//                println(q)
                val (cost, x, y, dd) = q.poll()
                if (x == map.size - 1 && y == map[0].size - 1) {
                    if (debug) println(costs.size)
                    return cost // destination
                }

                for (direction in 0 until 4) {
                    var costIncrease = 0
                    if (direction == dd || (direction + 2) % 4 == dd) {
                        continue // we cannot go further the same way , since the limits
                    }

                    for (distance in 1..maxdist) {
                        val xx = x + dirs[direction].first * distance
                        val yy = y + dirs[direction].second * distance

                        if (valid(xx, yy,map.size, map[0].size)) {
                            costIncrease += map[xx][yy]
                            if (distance < mindist) {
                                continue
                            }

                            val newState = Triple(xx, yy, direction)
                            val nc = cost + costIncrease

                            if (costs.getOrDefault(newState, Int.MAX_VALUE) <= nc) {
                                continue // been there with less cost
                            }
                            costs[newState] = nc
                            q.offer(State(nc, xx, yy, direction))
                            if (debug) println(nc.toString() + " " +  xx.toString() + " " +  yy.toString() + " ")

                        }
                    }
                }
            }
            return -1 // FAILED



    }



    }
}