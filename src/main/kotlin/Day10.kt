fun main(args: Array<String>) {

    val inputData = Common.getData(10, if (args.size>0) args.get(0) else "dev")


    var inputData1 = "..F7.\n" +
            ".FJ|.\n" +
            "SJ.L7\n" +
            "|F--J\n" +
            "LJ..."

    val inputData2 = "...........\n" +
            ".S-------7.\n" +
            ".|F-----7|.\n" +
            ".||.....||.\n" +
            ".||.....||.\n" +
            ".|L-7.F-J|.\n" +
            ".|..|.|..|.\n" +
            ".L--J.L--J.\n" +
            "..........."
   val inputData3 = "..........\n" +
           ".S------7.\n" +
           ".|F----7|.\n" +
           ".||....||.\n" +
           ".||....||.\n" +
           ".|L-7F-J|.\n" +
           ".|..||..|.\n" +
           ".L--JL--J.\n" +
           ".........."
    val inputData4= ".F----7F7F7F7F-7....\n" +
            ".|F--7||||||||FJ....\n" +
            ".||.FJ||||||||L7....\n" +
            "FJL7L7LJLJ||LJ.L-7..\n" +
            "L--J.L7...LJS7F-7L7.\n" +
            "....F-J..F7FJ|L7L7L7\n" +
            "....L7.F7||L7|.L7L7|\n" +
            ".....|FJLJ|FJ|F7|.LJ\n" +
            "....FJL-7.||.||||...\n" +
            "....L---J.LJ.LJLJ..."
    val inputData5="FF7FSF7F7F7F7F7F---7\n" +
            "L|LJ||||||||||||F--J\n" +
            "FL-7LJLJ||||||LJL-77\n" +
            "F--JF--7||LJLJ7F7FJ-\n" +
            "L---JF-JLJ.||-FJLJJ7\n" +
            "|F|F-JF---7F7-L7L|7|\n" +
            "|FFJF7L7F-JF7|JL---7\n" +
            "7-L-JL7||F7|L7F-7F7|\n" +
            "L.L7LFJ|||||FJL7||LJ\n" +
            "L7JLJL-JLJLJL--JLJ.L"
    // Day10.Game_10_01(inputData1.split("\n"),false)

    println( Day10.Game_01(inputData.split("\n"),false))
    println( Day10.Game_02(inputData2.split("\n"),false))
    println( Day10.Game_02(inputData3.split("\n"),false))
    println( Day10.Game_02(inputData4.split("\n"),false))
    println( Day10.Game_02(inputData5.split("\n"),false))

}

class Day10 {
    companion object {
    var visitedPositions = mutableListOf<Pair<Int,Int>>()
        fun Game_02(inputLines: List<String>, debug: Boolean):Int {
            // init if needed
            //println(visitedPositions)
            init(inputLines,debug)
        // init
            val map = MutableList(inputLines.size) { MutableList(inputLines[0].length) { '0' } }
            for (y in 0 until inputLines.size) {
                    for (x in 0 until inputLines[y].length) {
                        if (!visitedPositions.contains(Pair(x,y))) {
                            map[y][x] = if (isIn(inputLines,Pair(x,y))) { '1' } else { '0' }
                        } else {
                            // if it is a border than it is not counts
                            map[y][x] = inputLines[y][x]
                        }
                    }
            }

            println(map)
            return 1
        }

        // ray trace,
    fun isIn(inputLines: List<String>,pos: Pair<Int,Int>) : Boolean{
        if (visitedPositions.contains(pos)) return false
            // we have to count the border crossings
            var borders = 0
        for (i in pos.first .. 0 ) {
            if (inputLines[pos.second][i] in setOf<Char>('L','J','7','F','-') ) borders+=1
        }
        // if the border corossing is odd so we are int
        return borders % 2 == 1
    }
    fun init(inputLines: List<String>, debug: Boolean){
        visitedPositions = mutableListOf<Pair<Int,Int>>()
        // find the starting poision
        var pos = findS(inputLines)?:Pair(0,0)
        visitedPositions.add(pos)
        println(pos)
        // first move
        pos = Pair(pos.first,pos.second+1) // this has to be a legit step
        var direction: Int = 1
        /*
         1 up
         2 down
         3 left
         4 right
         */
//    println(inputLines)
        // moving around
        while (visitedPositions.get(0)!=pos) {
            //  sum = sum + 1
            //   println(inputLines[positionY][positionX])
            when (inputLines[pos.second][pos.first]) {
                '|' -> {
                    when (direction) {
                        1 -> pos = Pair(pos.first,pos.second - 1)
                        2 -> pos = Pair(pos.first,pos.second + 1)
                    }
                }

                '-' -> {
                    when (direction) {
                        3 -> pos = Pair(pos.first-1,pos.second)
                        4 -> pos = Pair(pos.first+1,pos.second)
                    }
                }

                'L' -> {
                    when (direction) {
                        2 -> {
                            pos = Pair(pos.first+1,pos.second)
                            direction = 4
                        }

                        3 -> {
                            pos = Pair(pos.first,pos.second - 1)
                            direction = 1
                        }
                    }
                }

                'J' -> {
                    when (direction) {
                        2 -> {
                            pos = Pair(pos.first-1,pos.second)
                            direction = 3
                        }

                        4 -> {
                            pos = Pair(pos.first,pos.second - 1)
                            direction = 1
                        }
                    }
                }

                'F' -> {
                    when (direction) {
                        1 -> {
                            pos = Pair(pos.first+1,pos.second)
                            direction = 4
                        }

                        3 -> {
                            pos = Pair(pos.first,pos.second+1)
                            direction = 2
                        }
                    }
                }

                '7' -> {
                    when (direction) {
                        1 -> {
                            pos = Pair(pos.first-1,pos.second)
                            direction = 3
                        }

                        4 -> {
                            pos = Pair(pos.first,pos.second + 1)
                            direction = 2
                        }
                    }
                }
            }
            visitedPositions.add(pos)
            // this is the full circle length
            if (debug) println(visitedPositions.size)
        }

    }
    fun Game_01(inputLines: List<String>, debug: Boolean):Int {
        // the furthest you can get, the half of the circle
        init(inputLines,debug)
        return ((visitedPositions.size + 1) / 2)
    }

    fun findS(inputLines: List<String>): Pair<Int, Int>? {
        inputLines.forEachIndexed { y, it ->
            it.forEachIndexed { x, it2 ->
                if (it2.equals('S')) {
                    return Pair(x,y)
                }
            }
        }
        return null
    }

}
}