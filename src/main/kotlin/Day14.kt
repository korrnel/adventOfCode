fun main(args: Array<String>) {

    val inputData = Common.getData(14)


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
}

class Day14 {
    companion object {
        fun Game_01(inputLines: List<String>, debug: Boolean) : Int {
            val newGrid = MutableList(inputLines.size) { MutableList(inputLines[0].length) { ' ' } }
            println(inputLines)
            for (row in 0 until inputLines.size) {
                for (col in 0 until inputLines[0].length) {
                    newGrid[row][col] = inputLines[row][col]
                }
            }
            println(newGrid)
            var ordered = orderThem(newGrid,debug)
            println(ordered)
            return sumIt(ordered,true)

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