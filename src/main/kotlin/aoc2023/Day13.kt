package aoc2023

fun main(args: Array<String>) {

    val inputData = Common.getData(13, args[0])


    var inputData1 =
                        "#.##..##.\n" +
                        "..#.##.#.\n" +
                        "##......#\n" +
                        "##......#\n" +
                        "..#.##.#.\n" +
                        "..##..##.\n" +
                        "#.#.##.#.\n" +
                        "\n" +
                        "#...##..#\n" +
                        "#....#..#\n" +
                        "..##..###\n" +
                        "#####.##.\n" +
                        "#####.##.\n" +
                        "..##..###\n" +
                        "#....#..#"
        println( Day13.Game_01(inputData.trim().split("\n\n"),false))
}

class Day13 {
    companion object {
        fun Game_01(inputLines: List<String>, debug: Boolean) : Int {
            //val newGrid = MutableList(inputLines.size) { MutableList(inputLines[0].length) { ' ' } }
            var sum = 0
            var sum2 = 0
            inputLines.forEachIndexed { i, s  ->
                val H =  horizontal(s.trim().split("\n"),debug)
                val H2 =  horizontal2(s.trim().split("\n"),debug)

                val V =  horizontal(rotateItClockwise(s.trim().split("\n").toList(),debug),debug)
                val V2 = horizontal2(rotateItClockwise(s.trim().split("\n").toList(),debug),debug)

                if (V>0) { sum = sum + V }
                if (H>0) { sum = sum + H*100 }
                if (V2>0) { sum2 = sum2 + V2 }
                if (H2>0) { sum2 = sum2 + H2*100 }
                println ("H2 - " + H2 +" H - " + H + " V2 -" + V2.toString() + " V - "+ V + " -> " + sum.toString())
            }
            println(sum)
            return sum2
        }

        fun horizontal2(inputLine : List<String>,debug: Boolean) : Int{
            var max = -1
            inputLine.forEachIndexed { index, line ->
                for (i in index..inputLine.size-1){
                    if(horizontalSame2(inputLine,i, debug)&&max<i) max=i
                }
            }
            return max
        }
        fun horizontalSame2(inputLine: List<String>, start : Int,debug: Boolean) : Boolean{
                var list1 = listOf<String>()
                var list2 = listOf<String>()
                if (start*2<inputLine.size){
                    list1= inputLine.subList(0,start)
                    list2= inputLine.subList(start,start*2).reversed()
                } else {
                    list1=inputLine.subList(start-(inputLine.size-start),start)
                    list2=inputLine.subList(start,inputLine.size).reversed()
                    if (debug) println((inputLine.size-start))
                }
           //  println(list1)
           //  println(list2)
           //  println(list1.equals(list2))
            return list1.equals(list2)
            }

            fun horizontal(inputLine : List<String>,debug: Boolean) : Int{
            var max = -1
            inputLine.forEachIndexed { index, line ->
                for (i in index..inputLine.size-1){
                    if(horizontalSame(inputLine,i, debug)&&max<i) max=i
                }
            }
            return max
        }

        fun horizontalSame(inputLine: List<String>, start : Int,debug: Boolean) : Boolean{
            var OK = true
            for (i in 0..inputLine.size-start-1){
                if (start-i-1<0) return true
                if (debug) println("B-" + start.toString() + " " + i.toString() + "-.>" + inputLine[start+i] + "---" + inputLine[start-i-1] + "->")
                if (!(inputLine[start+i].equals(inputLine[start-i-1]))) { return false}
            }
            if (debug) println(start.toString() + "EQ!!")
            return OK
        }

        fun rotateItClockwise(inputLines: List<String>,debug: Boolean ) : List<String>{
            val newGrid = MutableList(inputLines[0].length) { MutableList(inputLines.size) { ' ' } }
         //   if (debug) println(inputLines)
            // transpose
            for (row in 0 until newGrid.size) {
                for (col in 0 until newGrid[0].size) {
                    newGrid[row][col]=inputLines[col][row]
                    //newGrid[inputLines[0].size-row-1][inputLines.size-col-1] = inputLines[row][col]
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