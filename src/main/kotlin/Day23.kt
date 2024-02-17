import java.util.*

fun main(args: Array<String>) {

    val inputData = Common.getData(23, args[0])


    var inputData1 =
                            "#.#####################\n" +
                            "#.......#########...###\n" +
                            "#######.#########.#.###\n" +
                            "###.....#.>.>.###.#.###\n" +
                            "###v#####.#v#.###.#.###\n" +
                            "###.>...#.#.#.....#...#\n" +
                            "###v###.#.#.#########.#\n" +
                            "###...#.#.#.......#...#\n" +
                            "#####.#.#.#######.#.###\n" +
                            "#.....#.#.#.......#...#\n" +
                            "#.#####.#.#.#########v#\n" +
                            "#.#...#...#...###...>.#\n" +
                            "#.#.#v#######v###.###v#\n" +
                            "#...#.>.#...>.>.#.###.#\n" +
                            "#####v#.#.###v#.#.###.#\n" +
                            "#.....#...#...#.#.#...#\n" +
                            "#.#########.###.#.#.###\n" +
                            "#...###...#...#...#.###\n" +
                            "###.###.#.###v#####v###\n" +
                            "#...#...#.#.>.>.#.>.###\n" +
                            "#.###.###.#.###.#.#v###\n" +
                            "#.....###...###...#...#\n" +
                            "#####################.#"
        println( Day23.Game_01(inputData.trim(),1,1,false))
    // this will fail. the map shold be recreated as nodes first when there's alternate routes
        println( Day23.Game_01(inputData.replace('>','.')
            .replace('<','.').replace('v','.').replace('^','.')
            .trim(),1,1,false))
}

class Day23 {
    companion object {

        data class State(val cost: Int, val x: Int, val y: Int, val dd: Int,val past: List<Pair<Int, Int>>)

        data class Path(val x: Int, val y: Int, val cost: Int, val direction: Int, val past : List<Pair<Int, Int>>)

        fun Game_01(inputLines: String,mindist: Int, maxdist: Int,  debug: Boolean) : Int {
            val results = mutableListOf<Int>()
            // val map = inputLines.map { line -> line.map { it.digitToInt() } }
            val map = inputLines.split("\n")

            var path = mutableListOf<Path>()

            println(map)
            val q = PriorityQueue<State>(compareBy { it.cost })
            q.offer(State(0, 1, 0, -1, listOf(Pair(1,0)))) // starting point
// GO
            while (q.isNotEmpty()) {
                if (debug) println(q)
                val (cost, x, y, dd,past) = q.poll()
                var pastPath = mutableListOf<Pair<Int,Int>>()
                pastPath=past.toMutableList()
               // if (debug) println(map[x][y])
          //      println(map[x])
                // target location
                if (x == map[0].length -2 && y == map.size - 1) {
                    results.add(cost)
                    if (debug) println("Success - " +  cost.toString()) // destination
                }

                for (direction in 0 until 4 ) {
                    var costIncrease = 1
                    //println(direction.toString())
                    if (!validDirections(map,x,y,direction,dd)) {
                        continue // not valid
                    }

                    if (debug) println(direction.toString() + "-OK")
                    val xx = x + dirs[direction].first
                    val yy = y + dirs[direction].second
                    val newState = Triple(xx, yy, direction)
                    val nc = cost + costIncrease

                    if (pastPath.contains(Pair(xx,yy))) {
                             continue // been here
                    }
                    // add to the ones need to try
                    //path.add(Path(xx,yy,nc,direction))
                    pastPath.add(Pair(xx,yy))
                    q.offer(State(nc, xx, yy, direction,pastPath))
                    if (debug) println(nc.toString() + " " +  xx.toString() + " " +  yy.toString() + " ")



                }
            }
            // no more steps
            println(results)
            return results.max()



        }

        val dirs = listOf(Pair(0, -1), Pair(1, 0),Pair(0, 1),  Pair(-1, 0))
        private fun validDirections(map: List<String>, x: Int, y: Int,direction: Int,dd: Int): Boolean {

            if ((map[y][x]=='^' )&&(direction==0)) return true
            if ((map[y][x]=='>' )&&(direction==1)) return true
            if ((map[y][x]=='v' )&&(direction==2)) return true
            if ((map[y][x]=='<' )&&(direction==3)) return true
            if ( map[y][x]=='.' ){
                    val xx = x + dirs[direction].first
                    val yy = y + dirs[direction].second
                    val maxY = map.size
                    val maxX = map[0].length
                    if ( xx>-1 && xx<maxX && yy>-1 &&yy < maxY) {
                      if (map[yy][xx]=='#') return false
                      return  true
                    }
              }

            return false
        }

    }
}