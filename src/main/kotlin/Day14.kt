fun main(args: Array<String>) {

    val inputData = Common.getData(14, args[0])


    var inputData1 =
                "O....#....\n" +
                "O.OO#....#\n" +
                ".....##...\n" +
                "OO.#O....O\n" +
                ".O.....O#.\n" +
                "O.#..O.#.#\n" +
                "..O..#O..O\n" +
                ".......O..\n" +
                "#....###..\n" +
                "#OO..#...."
    val inputData2=
            "OOOO.#.O..\n" +
            "OO..#....#\n" +
            "OO..O##..O\n" +
            "O..#.OO...\n" +
            "........#.\n" +
            "..#....#.#\n" +
            "..O..#.O.O\n" +
            "..O.......\n" +
            "#....###..\n" +
            "#....#...."
        println( Day14.Game_01(inputData.trim().split("\n"),false))
        println( Day14.Game_02(inputData.trim().split("\n"),false))
}

class Day14 {
    companion object {
        fun Game_02(inputLines: List<String>, debug: Boolean) : Int {
            val newGrid = MutableList(inputLines.size) { MutableList(inputLines[0].length) { ' ' } }
            println(inputLines)
            for (row in 0 until inputLines.size) {
                for (col in 0 until inputLines[0].length) {
                    newGrid[row][col] = inputLines[row][col]
                }
            }
            println(newGrid)
            var ordered = newGrid
            for (i in 1..1000) {

             ordered = orderThem(ordered,debug) // North
             ordered = rotateItCounter(ordered,debug)
             ordered = orderThem(ordered,debug) // West
             ordered = rotateItCounter(ordered,debug)
             ordered = orderThem(ordered,debug) // south
             ordered = rotateItCounter(ordered,debug)
             ordered = orderThem(ordered,debug) // east
             ordered = rotateItCounter(ordered,debug)
                println(i.toString() + " " + sumIt(ordered,debug))
                /*
                *  after a few rounds the sum is repeating,
                *  in the example is after the 5th  and 7 length cycles so
                *  ( 1000000000 - 5 ) mod 7 = 1 so the second element of the pattern
                *
                * 65
                * 64 <---
                * 65
                * 63
                * 68
                * 69
                * 69
                *
                *  in the input it's repeating after the 152th and 17 is the length or the pattern
                * ( 1000000000 - 152 ) mod 17 = 8 so the 9th element of the pattern
                * 89106
                * 89078
                * 89047
                * 89048
                * 89048
                * 89044
                * 89049
                * 89058
                * 89089 <---
                * 89119
                * 89150
                * 89173
                * 89170
                * 89160
                * 89171
                * 89167
                * 89133
                *
                * */

            }
            //    println(ordered)
            return sumIt(ordered,debug)

        }
        fun rotateItCounter(inputLines: MutableList<MutableList<Char>>,debug: Boolean ) : MutableList<MutableList<Char>>{
            val newGrid = MutableList(inputLines[0].size) { MutableList(inputLines.size) { ' ' } }
            if (debug) println(inputLines)
            // transpose
            for (row in 0 until newGrid.size) {
                for (col in 0 until newGrid.size) {
                 newGrid[row][col]=inputLines[col][row]
                //newGrid[inputLines[0].size-row-1][inputLines.size-col-1] = inputLines[row][col]
                }
            }
            val newGrid2 = MutableList(newGrid.size) { MutableList(newGrid[0].size) { ' ' } }
            // reverse
            for (row in 0 until newGrid.size) {
                for (col in 0 until newGrid.size) {
                    newGrid2[row][col]=newGrid[row][newGrid.size-col-1]

                }
            }
            return  newGrid2
        }
        fun Game_01(inputLines: List<String>, debug: Boolean) : Int {
            val newGrid = MutableList(inputLines.size) { MutableList(inputLines[0].length) { ' ' } }
            if (debug) println(inputLines)
            for (row in 0 until inputLines.size) {
                for (col in 0 until inputLines[0].length) {
                    newGrid[row][col] = inputLines[row][col]
                }
            }
            if (debug) println(newGrid)
            var ordered = orderThem(newGrid,debug)
            if (debug) println(ordered)
            return sumIt(ordered,debug)

        }
        fun orderThem(data: MutableList<MutableList<Char>>,debug: Boolean ) : MutableList<MutableList<Char>>{
            var data2 = data
            for (i in 0..data2.size-1)
            {
                data2[i].forEachIndexed { index, c ->
                    if (debug) println(data2)
                    if (c=='.') {
                        if (debug) println("seek " + i.toString() + "-" + index.toString())
                      data2=swap(data2,i,index,debug)
                    }
                }
            }
            return data2
        }
        fun swap(data: MutableList<MutableList<Char>>, row : Int,column : Int,debug: Boolean) : MutableList<MutableList<Char>> {
            var data2 = data
               if (data2[row][column]=='.') {
                   val swap = findRock(data2,row,column,debug)
                   if (swap>0) {
                       var tmp = data2.get(swap)
                       tmp.set(column,'.')
                       data2.set(swap,tmp)
                       tmp = data.get(row)
                       tmp.set(column,'O')
                       data2.set(row,tmp)
                   }

               }
            return  data2
        }
        fun findRock(inputLines:MutableList<MutableList<Char>>, row :Int, column : Int,debug: Boolean ) : Int {
            for (i in row + 1..inputLines.size-1) {
                if (debug) println(i.toString() + " " + column.toString())
                if (inputLines[i][column] == 'O') {
                    return i
                }
                if (inputLines[i][column] == '#') {
                    return -1
                }
             }
            return -1
        }
        fun sumIt(inputLines:MutableList<MutableList<Char>>,debug: Boolean ) : Int {
            var sum=0
             for (i in (0..inputLines.size-1))
             {
                 inputLines[i].forEach {  if (it.equals('O')) sum= sum + (inputLines.size-i) }
                 if(debug) println(sum)
             }
            return  sum
        }

}
}