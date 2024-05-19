package aoc2022

import com.sun.jna.platform.win32.Guid
import com.sun.jna.platform.win32.Guid.GUID
import java.util.UUID
import kotlin.math.sign


fun  main(args: Array<String>) {
// rock paper scissor
    val inputData = Common.getData(8, if (args.size > 0) args.get(0) else "dev", "2022")
    val inputData1 = "30373\n" +
            "25512\n" +
            "65332\n" +
            "33549\n" +
            "35390"

// part 1  - how many trees are visible from outside the grid?
    println( Day08.Game_01(inputData1.trim(), false))
    println( Day08.Game_01(inputData.trim(), false))

// part 2  - how many trees are visible from inside of the grid from a tree house
  //  println( Day08.Game_02(inputData1.trim(), false))
    println( Day08.Game_02(inputData.trim(), true))



}
class Day08 {
    companion object {
        data class Node(val i: Int) {
        }
        fun Game_02(inputLines: String,debug: Boolean): Int {
            var map = inputLines.split("\n").map { it.trim().split("") }
            var scores = mutableMapOf<Pair<Int,Int>,Int>()
            println(map[0])
            if (1==1) map.forEachIndexed { row, strings ->
                strings.forEachIndexed { column, s ->
                        try {
                            scores.put(Pair(row,column),getScore(row,column,s.toInt(),map))
                        } catch (e: NumberFormatException) {
                            null
                        }
                    }
            }
            if (debug) println(scores)

            if (debug) println(map[1][10].toInt())
            if (debug) println(getScore(1,10,map[1][10].toInt(),map))
            return scores.values.max()
        }

        private fun getScore(row: Int, column: Int, treeHouse: Int, map : List<List<String>>): Int {
            // go left
            var sum = 1
            var visible = 0
            for (col in (1 until column).reversed() ) { // it starts with  empty col
                visible++
                try {
                    if (map[row][col].toInt()>=treeHouse) break
                } catch (e: NumberFormatException)
                {
                    break
                }
            }
            // println(visible)
            if (visible>0) sum*= visible
            // go right
            visible = 0
            for (col in (column+1 until map[row].size-1) ) { // it ends with an empty col
                visible++
                try {
                    if (map[row][col].toInt()>=treeHouse) break
                } catch (e: NumberFormatException)
                {
                    break
                }

            }
            // println(visible)
            if (visible>0) sum*= visible
            // go down
            visible = 0
            for (r in (row+1 until map.size) ) {
                visible++
                try {
                    if (map[r][column].toInt()>=treeHouse) break
                } catch (e: NumberFormatException)
                {
                    break
                }

            }
            // println(visible)
            if (visible>0) sum*= visible
            // go up
            visible = 0
            for (r in (0 until row ).reversed() ) {
                visible++
                try {
                    if (map[r][column].toInt()>=treeHouse) break
                } catch (e: NumberFormatException)
                {
                    break
                }

            }
            // println(visible)
            if (visible>0) sum*= visible
            // println(row.toString() + " " + column.toString() + " " + map[row][column].toInt() + " " + sum.toString())
            return sum

        }

        fun Game_01(inputLines:String, debug: Boolean,): Int {
            var sum = mutableListOf<Pair<Int,Int>>()
            var map = inputLines.split("\n").map { it.trim().split("") }
            var max = -1
            // walk from right to left
            for (row in (0 until map.size)) {
                max = -1
                for ( col in (0 until map[row].size).reversed()) {
                    try { // if it is a number , so filled
                        if (map[row][col].toInt() > max) {
                            max=map[row][col].toInt()
                            if (!sum.contains(Pair(row,col))) sum.add(Pair(row,col))
                        }
                    } catch (e: NumberFormatException) {
                        null
                    }
                }
            }
            // walk from left to right
            for (row in (0 until map.size)) {
                max = -1
                for ( col in (0 until map[row].size)) {
                    try { // if it is a number , so filled
                        if (map[row][col].toInt() > max) {
                            max=map[row][col].toInt()
                            if (!sum.contains(Pair(row,col))) sum.add(Pair(row,col))
                        }
                    } catch (e: NumberFormatException) {
                        null
                    }
                }
            }
            // walk bottom to top
            for (col in (0 until map[0].size)){
                max = -1
                for (row in (0 until map.size).reversed()) {
                    try { // if it is a number , so filled
                        if (map[row][col].toInt() > max) {
                            max=map[row][col].toInt()
                            if (!sum.contains(Pair(row,col))) sum.add(Pair(row,col))
                        }
                    } catch (e: NumberFormatException) {
                        null
                    }
                }
            }
            // walk from top to bottom
            for ( col in (0 until map[0].size)){
                max = -1
                for (row in (0 until map.size)) {
                    try { // if it is a number , so filled
                        if (map[row][col].toInt() > max) {
                            max=map[row][col].toInt()
                            if (!sum.contains(Pair(row,col))) sum.add(Pair(row,col))
                        }
                    } catch (e: NumberFormatException) {
                        null
                    }
                }
            }
            // print the result colored
           if (debug) {
               for (row in (0 until map.size)) {
                   for (col in (0 until map[row].size)) {
                       if (sum.contains(Pair(row, col))) print("\u001b[32m") // set to green
                       print(map[row][col]) // print value
                       print("\u001b[0m") // to reset color to the default
                   }
                   println()
               }
           }
            //          init(inputLines,debug)
//            return nodes.filter { it.type== Node.NodeType.DIRECTORY }.filter { it.size<=SIZE_PARAM }.sumOf { it.size }
             return sum.size
        }



    }
}