import kotlin.math.abs

fun main(args: Array<String>) {

    val inputData = Common.getData(11, args[0])


    var inputData1 =
                "...#......\n" +
                ".......#..\n" +
                "#.........\n" +
                "..........\n" +
                "......#...\n" +
                ".#........\n" +
                ".........#\n" +
                "..........\n" +
                ".......#..\n" +
                "#...#....."


    println(Day11.Game_01(inputData.trim(),true)) // 9521776
    println(Day11.Game_02(inputData1.trim(),1,true))
    println(Day11.Game_02(inputData1.trim(),10-1,true))
    println(Day11.Game_02(inputData1.trim(),100-1,true))
    println(Day11.Game_02(inputData.trim(),1000000-1,true))
}

class Day11 {
    companion object {
    var expandedCol = mutableListOf<Int>()
    var expandedRow = mutableListOf<Int>()
        fun Game_01(inputLines: String,  debug: Boolean): Long {
            var galaxy = inputLines.split('\n').toMutableList()
            // expand columns
            galaxy = expand(galaxy)
            galaxy = rotateItClockwise(galaxy.toList(),debug)
            // expand rows
            galaxy = expand(galaxy)
            var pairs= findPairs(galaxy)
            println(pairs)
            var sum=0L
            pairs.forEachIndexed { index , it ->
                for (i in index+1 until pairs.size) {
                    sum = sum + distance(pairs[index],pairs[i])
                }
            }
            return  sum
        }
    fun Game_02(inputLines: String,rate: Int,  debug: Boolean): Long {
        var galaxy = inputLines.split('\n').toMutableList()
        expandedCol.clear()
        expandedRow.clear()
        //  just find the cols
        var  galaxy2 = expand2(galaxy,0)
        galaxy2 = rotateItClockwise(galaxy2.toList(),debug)
        // just find the rows
        galaxy2 = expand2(galaxy2,1)
        var pairs= findPairs(galaxy)
        var sum=0L
        pairs.forEachIndexed { index , it ->
            for (i in index+1 until pairs.size) {
                sum = sum + distance2(pairs[index],pairs[i],rate)
            }
        }

        return  sum
    }

        private fun distance(pair: Pair<Long, Long>, pair1: Pair<Long, Long>):Long {
            return abs(pair.first-pair1.first) + abs(pair.second-pair1.second)
        }
        private fun distance2(pair: Pair<Long, Long>, pair1: Pair<Long, Long>,rate:Int):Long {
            var first=0
            if (pair1.first>pair.first) {
                for (i in pair.first..pair1.first) if (expandedRow.contains(i.toInt())) first++
            } else {
                for (i in pair1.first..pair.first) if (expandedRow.contains(i.toInt())) first++
            }
            var second=0
            if (pair1.second>pair.second) {
                for (i in pair.second..pair1.second) if (expandedCol.contains(i.toInt())) second++
            } else {
                for (i in pair1.second..pair.second) if (expandedCol.contains(i.toInt())) second++
            }

            return abs(pair.first-pair1.first)+ first*rate + abs(pair.second-pair1.second)+ second*rate
        }

        fun findPairs2(galaxy: List<String>) : MutableList<Pair<Long, Long>>{
            var pairs  = mutableListOf<Pair<Long, Long>>()
            for (row in 0 until galaxy.size) {
                for (col in 0 until galaxy[0].length) {
                    if (galaxy[row][col]=='#') pairs.add(Pair(row.toLong(),col.toLong()))
                }
            }
            return  pairs
        }
        fun findPairs(galaxy: List<String>) : MutableList<Pair<Long, Long>>{
            var pairs  = mutableListOf<Pair<Long, Long>>()
            for (row in 0 until galaxy.size) {
                for (col in 0 until galaxy[0].length) {
                    if (galaxy[row][col]=='#') pairs.add(Pair(row.toLong(),col.toLong()))
                }
            }
            return  pairs
        }
        fun expand2(galaxy: List<String>, dir:Int): MutableList<String>{
            var expandedY= mutableListOf<String>()

            // expand rows
            galaxy.forEachIndexed { index,it->
                expandedY.add(it)
                if (!it.contains('#')) {
                    expandedY.add(it)
                    if (dir==0) expandedRow.add(index)
                    if (dir==1) expandedCol.add(index)

                }
            }
            return  expandedY
        }
        fun expand(galaxy: List<String>): MutableList<String>{
            var expandedY= mutableListOf<String>()

            // expand rows
            galaxy.forEachIndexed { index,it->
                expandedY.add(it)
                if (!it.contains('#')) {
                    expandedY.add(it)
                }
            }
            return  expandedY
        }
        fun rotateItClockwise(inputLines: List<String>,debug: Boolean ) : MutableList<String>
        {
            val newGrid = MutableList(inputLines[0].length) { MutableList(inputLines.size) { ' ' } }
            //   if (debug) println(inputLines)
            // transpose
            for (row in 0 until newGrid.size) {
                for (col in 0 until newGrid[0].size) {
                    newGrid[row][col]=inputLines[col][row]
                }
            }
            val newGrid2 = MutableList(newGrid.size) { MutableList(newGrid[0].size) { ' ' } }
            // reverse
            for (row in 0 until newGrid2.size) {
                for (col in 0 until newGrid2[0].size) {
                    newGrid2[row][col]=newGrid[row][newGrid2[0].size-col-1]

                }
            }
            val newGrid3 = mutableListOf<String>()
            for (row in 0 until newGrid2.size) {
                var s : String = ""
                for (col in 0 until newGrid2[0].size)  s=s+newGrid2[row][col]
                newGrid3.add(s)
            }
            return  newGrid3
        }

    }
}