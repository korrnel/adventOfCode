fun main(args: Array<String>) {

    val inputData = Common.getData(3, args[0])

    var inputData1 =
        "467..114...\n" +
                "...*......\n" +
                "..35..633.\n" +
                "......#...\n" +
                "617*......\n" +
                ".....+.58.\n" +
                "..592.....\n" +
                "......755.\n" +
                "...\$.*....\n" +
                ".664.598..\n" +
                ""
    Day03.Game_03(inputData1.split("\n"),false)
    Day03.Game_03_02(inputData1.split("\n"),false)

    Day03.Game_03(inputData.split("\n"),false)
    Day03.Game_03_02(inputData.split("\n"),false)
}
class Day03 {
    companion object {
        data class FoundOne(
            val number: String,
            val area: Set<Pair<Int, Int>>
        )
    fun Game_03_02(inputLines: List<String>, debug : Boolean) {
        // find the numbers connected with an asterix * and multiply them
        var sum = 0
        var numbers = mutableListOf<FoundOne>()
        // collect numbers
        inputLines.forEachIndexed { i, it ->
            var number = ""
            var start = -1
            it.forEachIndexed() { j, it2 ->
                if ((it2.isDigit())) {
                    if (start < 0) {
                        start = j
                    }
                    number = number + it2
                }
                if (!(it2.isDigit()) or (j == it.length - 1)) {
                    if (start > -1) {
                        numbers.add(FoundOne(number, getArea(i,j,number)))
                        if (debug) {
                            println("at line" + i + " -- " + number)
                        }
                        number=""
                        start= -1
                    }
                }
            }
        }
        // numbers collected
        inputLines.forEachIndexed { i, it ->
            var partialPower = 1
            it.forEachIndexed() { j, it2 ->
                if (it2.equals('*')) {
                    val whereIm  = setOf(Pair(i,j))
                    println(i.toString() + "-" + j.toString()+ " Near")
                    var Found = mutableListOf<Int>()
                    numbers.forEach { item ->

                        if(item.area.intersect(whereIm).isNotEmpty()) {
                            Found.add(item.number.toInt())
                            if (debug) {
                                println(item.number + " Has found ")
                            }

                        }
                    }
                    // if two numbers are linked by an * then add them
                    if (Found.size==2) sum = sum + Found[0]*Found[1]
                    if (debug) {
                        println(sum)
                    }

                }
            }
            //sum = sum + partialPower
        }
        println(sum)
    }

    fun getArea(row : Int, column :Int , number : String) : MutableSet<Pair<Int, Int>>
    {
        var result = mutableSetOf<Pair<Int, Int>>()
        val delta = 1
        for (j in row-delta..row+delta)
        {
            for (i in column-delta-number.length..column  ){
                result.add(Pair(j,i))
                //   println(j.toString() + " " + i.toString() + "  - "  + number)
            }

        }
        return result
    }

    fun Game_03(inputLines: List<String>, debug : Boolean) {
        // part 1 find the numbers next to a symbol

        var sum = 0
        var alreadyIn : IntArray = intArrayOf()

        inputLines.forEachIndexed { i, it ->
            var number = ""
            var start = -1
            // find the numbers
            it.forEachIndexed() { j, it2 ->

                if ((it2.isDigit())) {
                    if (start < 0) {
                        start = j
                    }
                    number = number + it2
                }

                if (!(it2.isDigit()) or (j==it.length-1)) {
                    if (start > -1) {
                        if (debug) {
                            print("at line" + i + " -- " + number)
                        }
                        // is near a symbol
                        if (isAPart(i, start, j, inputLines)) {
                            if (debug) {
                                print(" - PART ")
                            }
                            sum = sum + number.toInt()

                        }
                        start = -1
                        number = ""
                        if (debug) {
                            println(" ")
                        }
                    }
                }

            }
            if (debug) {
                println(sum)
            }
        }
        println(sum)
        println(alreadyIn.size)

        // 526868 is bad since it doesn't consider the numbers at the end of the lines

        // 521601 is the solution
    }
    fun isAPart(line : Int, colStart: Int, colEnd: Int, lineList : List<String>):Boolean
    {
        // seek the number's surroundings  for a symbol
        var startLine = if (line<1) line else line - 1
        var startColumn = if (colStart<1) colStart else colStart-1
        var endColumn = if (lineList[line].length-1==colEnd) colEnd-1 else colEnd
        if (line>0)
        {
            for (i in startColumn..endColumn ){
                if(!((lineList[startLine][i].isDigit()) or (lineList[startLine][i]=='.')))
                { return true }
            }
        }

        for (i in startColumn..endColumn ){
            if(!((lineList[line][i].isDigit()) or (lineList[line][i]=='.')))
            { return true }
        }

        if (lineList.size-2>line) {
            for (i in startColumn..endColumn ){
                if(!((lineList[line+1][i].isDigit()) or (lineList[line+1][i]=='.')))
                { return true }
            }
        }
        return false
    }

    }
}