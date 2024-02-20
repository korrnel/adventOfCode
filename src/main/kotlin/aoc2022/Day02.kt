package aoc2022

fun  main(args: Array<String>) {
// rock paper scissor
    val inputData = Common.getData(2, if (args.size > 0) args.get(0) else "dev", "2022")
    val inputData1 = "A Y\n" +
            "B X\n" +
            "C Z"

    println( Day02.Game_01(inputData1.split("\n"), false))
    println( Day02.Game_01(inputData.trim().split("\n"), false))

    println( Day02.Game_02(inputData1.split("\n"), false))
    println( Day02.Game_02(inputData.trim().split("\n"), false))
}
class Day02 {
    companion object {
        fun getValue(hand: Pair<Char,Char>) : Int {
            var  sum= 0

            // A rock 1 , B paper 2 ,C scissor 3
            // x rock 1 , y paper 2 ,Z scissor 3
            sum += if (hand.second=='X') 1 else 0
            sum += if (hand.second=='Y') 2 else 0
            sum += if (hand.second=='Z') 3 else 0

            // win 6 ,draw 3 ,  lose 0
            val games = mapOf(
                Pair('A','X') to 3,
                Pair('A','Y') to 6,
                Pair('A','Z') to 0,

                Pair('B','X') to 0,
                Pair('B','Y') to 3,
                Pair('B','Z') to 6,

                Pair('C','X') to 6,
                Pair('C','Y') to 0,
                Pair('C','Z') to 3
            )
            return sum + games.get(hand)!!
        }
        fun getValue2(hand: Pair<Char,Char>) : Int {
            var  sum= 0

            // A rock 1 , B paper 2 , C scissor 3
            // x lose   , y draw    ,Z win
            sum += if (hand.second=='X') 0 else 0
            sum += if (hand.second=='Y') 3 else 0
            sum += if (hand.second=='Z') 6 else 0

            val games = mapOf(
                Pair('A','X') to 3,
                Pair('A','Y') to 1,
                Pair('A','Z') to 2,

                Pair('B','X') to 1,
                Pair('B','Y') to 2,
                Pair('B','Z') to 3,

                Pair('C','X') to 2,
                Pair('C','Y') to 3,
                Pair('C','Z') to 1
            )
            return sum + games.get(hand)!!

        }
        fun Game_01(inputLines: List<String>, debug: Boolean): Int {
        var sum = 0

        inputLines.forEach { it ->
            // commonChar(it.substring(0,it.length/2))
            var hand= it.split(' ')
            sum +=  getValue(Pair(hand[0][0],hand[1][0]))
           // println(it + " " + getValue(Pair(hand[0][0],hand[1][0])))

        }
        return sum

        }
        fun Game_02(inputLines: List<String>, debug: Boolean): Int {
            var sum = 0

            inputLines.forEach { it ->
                // commonChar(it.substring(0,it.length/2))
                var hand= it.split(' ')
                sum +=  getValue2(Pair(hand[0][0],hand[1][0]))
              //  println(it + " " + getValue2(Pair(hand[0][0],hand[1][0])))
            }
            return sum

        }

    }
}