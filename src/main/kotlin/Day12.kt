import org.jetbrains.kotlin.util.profile

fun main(args: Array<String>) {

    val inputData = Common.getData(12, if (args.size>0) args.get(0) else "dev")


    var inputData1 =
                "???.### 1,1,3\n" +
                ".??..??...?##. 1,1,3\n" +
                "?#?#?#?#?#?#?#? 1,3,1,6\n" +
                "????.#...#... 4,1,1\n" +
                "????.######..#####. 1,6,5\n" +
                "?###???????? 3,2,1"

    println( Day12.Game_01(inputData1.trim(),false))
}

class Day12 {
    companion object {
    fun Game_01(inputLines: String, debug: Boolean) : Int {
        var sum = 0
        val arrangements = inputLines.trim().split("\n"," ").filter { it.contains(',') }
        val springs = inputLines.trim().split("\n"," ").filter { !it.contains(',') }
        println(arrangements)
        println(springs)
        println(springs zip arrangements)
        return sum
    }

}
}