package aoc2022



fun  main(args: Array<String>) {
// rock paper scissor
    val inputData = Common.getData(6, if (args.size > 0) args.get(0) else "dev", "2022")
    val inputData1 = "mjqjpqmgbljsphdztnvjfqwrcgsmlb" // 7
    val inputData2 = "bvwbjplbgvbhsrlpgdmjqwftvncz" // 5
    val inputData3 = "nppdvjthqldpwncqszvftbrmjlhg" // 6
    val inputData4 = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg" // 10
    val inputData5 = "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw" // 11

// part 1  - 4 distinct char
    println( Day06.Game_01(inputData1, false,4))
    println( Day06.Game_01(inputData2, false,4))
    println( Day06.Game_01(inputData3, false,4))
    println( Day06.Game_01(inputData4, false,4))
    println( Day06.Game_01(inputData5, false,4))
    println( Day06.Game_01(inputData, false,4))

// part 2  - 14 distinct char
    println( Day06.Game_01(inputData1, false,14))
    println( Day06.Game_01(inputData2, false,14))
    println( Day06.Game_01(inputData3, false,14))
    println( Day06.Game_01(inputData4, false,14))
    println( Day06.Game_01(inputData5, false,14))
    println( Day06.Game_01(inputData, false,14))

}
class Day06 {
    companion object {

        fun distinct(s: String): String {
            var ret = ""
            s.forEach { if (!ret.contains(it)) ret+= it }
            return  ret
        }
        fun Game_01(inputLines: String, debug: Boolean, slice: Int): Int {

            for (i in 0 until inputLines.length-slice) {
                if (distinct(inputLines.substring(i,i+slice)).length==slice) return i+slice
            }
            return -1
        }


    }
}