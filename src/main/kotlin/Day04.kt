fun main(args: Array<String>) {

    val inputData = Common.getData(4,args[0])

    var inputData1 = "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53\n" +
            "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19\n" +
            "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1\n" +
            "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83\n" +
            "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36\n" +
            "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"

    println( Day04.Game_04_01(inputData1.split("\n"),false))
    println( Day04.Game_04_02(inputData1.trim().split("\n"),false))

    println( Day04.Game_04_01(inputData.split("\n"),false))
    println( Day04.Game_04_02(inputData.trim().split("\n"),false))
}

class Day04 {
    companion object {

    fun Game_04_02(inputLines: List<String>, debug: Boolean) : Int {
        // for each winnig card the next one counts as double
        var sum = 0
        var cards = mutableListOf<Int>()
        var extraCards = mutableListOf<Int>()
        // collect numbers
        inputLines.forEachIndexed { i, it ->
            var numbers = mutableListOf<String>()
            for (i in it.indexOf(':') + 2..it.indexOf('|') - 1 step 3) {
                // println(" -"+ it.substring(i)+ "-")
                // println(" -"+ it.substring(i,i + 2)+ "-")
                numbers.add(it.substring(i, i + 2).trim())

            }
            var found = -1
            for (i in it.indexOf('|') + 2..it.length - 1 step 3) {
                // println(" -"+ it.substring(i)+ "-")
                // println(" -"+ it.substring(i,i + 2)+ "-")
                if (numbers.contains(it.substring(i, i + 2).trim())) found = found + 1

            }
            // how many winning cards?
            cards.add(i,found+1)
            extraCards.add(1)
            if (debug) println(sum)

        }
        println(cards)
        // how many  cards there is ?
        cards.forEachIndexed { index,it ->
            val addition = extraCards[index] // how many we shoould add
            if (cards[index]>0){ // if it is a hit
                for (j in index+1 until index + 1 + it) {
                    extraCards.set(j, addition+extraCards[j]) // add it

                }
            }

        }
        // now summarize
        return (extraCards.sum())
    }
        private fun process(cards: List<Int>, start: Int, end: Int): Int {
            var ans = end - start
            for (i in start until end) {
                val wins = cards[i]
                ans += process(cards, i + 1, i + 1 + wins)
            }
            return ans
        }

        fun Game_04_01(inputLines: List<String>, debug: Boolean): Int {

        var sum = 0
        // collect numbers
        inputLines.forEachIndexed { i, it ->
            var numbers = mutableListOf<String>()
            // the first is the winning numbers
            for (i in it.indexOf(':') + 2..it.indexOf('|') - 1 step 3) {
                // println(" -"+ it.substring(i)+ "-")
                // println(" -"+ it.substring(i,i + 2)+ "-")
                numbers.add(it.substring(i, i + 2).trim())

            }
            var found = -1
            // the second set is the numbers i have
            for (i in it.indexOf('|') + 2..it.length - 1 step 3) {
                // println(" -"+ it.substring(i)+ "-")
                // println(" -"+ it.substring(i,i + 2)+ "-")
                if (numbers.contains(it.substring(i, i + 2).trim())) found = found + 1

            }
            // how many are a match?
            if (found > -1) sum = sum + Math.pow(2.0, found.toDouble()).toInt()
            if (debug) println(sum)

        }
        return (sum)
    }


}
}