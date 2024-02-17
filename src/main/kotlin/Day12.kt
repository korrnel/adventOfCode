fun main(args: Array<String>) {

    val inputData = Common.getData(12, args[0])


    var inputData1 =
        "Day10.kt???.### 1,1,3\n" +
                ".??..??...?##. 1,1,3\n" +
                "?#?#?#?#?#?#?#? 1,3,1,6\n" +
                "????.#...#... 4,1,1\n" +
                "????.######..#####. 1,6,5\n" +
                "?###???????? 3,2,1"

    println( Day12.Game_01(inputData1.trim().split("\n"),false))
}

class Day12 {
    companion object {
    fun Game_01(inputLines: List<String>, debug: Boolean) : Int {
        var sum = 0

        return sum
    }

}
}