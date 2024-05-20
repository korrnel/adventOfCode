package aoc2022

import com.sun.jna.platform.win32.Guid
import com.sun.jna.platform.win32.Guid.GUID
import java.util.UUID
import kotlin.math.sign


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
    println( Day09.Game_01(inputData1.trim(), false))
//    println( Day09.Game_01(inputData.trim(), false))



}
class Day09 {
    companion object {
        data class Node(val i: Int) {
        }

        fun Game_01(inputLines:String, debug: Boolean,): Int {
            var map = inputLines.split("\n").map { it.trim().split(" ") }
            return 1
        }



    }
}