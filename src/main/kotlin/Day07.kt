
fun main(args: Array<String>) {

    val inputData = Common.getData(7, args[0])


    var inputData1 = "32T3K 765\n" +
            "T55J5 684\n" +
            "KK677 28\n" +
            "KTJJT 220\n" +
            "QQQJA 483"

    timing {
        println(Day07.Game_01(inputData1.trim(),true))
    }
    timing {
        println(Day07.Game_01(inputData.trim(),true))
    }

}

class Day07 {
    companion object {

    data class Hand(val cards: String, val bid: Int, val value : Int)

    var stack =  mutableListOf<Hand>()

        fun Game_01(inputLines: String, debug: Boolean): Long {
            val hands = inputLines.split("\n").map { it.split(" ") }

            hands.forEach {
                println(it)

            }

            return  1
        }

    }
}