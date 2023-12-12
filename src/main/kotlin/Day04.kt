fun main(args: Array<String>) {

    val inputData = Common.getData(4)

    var inputData1 = "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53\n" +
            "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19\n" +
            "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1\n" +
            "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83\n" +
            "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36\n" +
            "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"

    Day04.Game_04_01(inputData1.split("\n"),false)
    Day04.Game_04_02(inputData1.split("\n"),false) // BAD

    Day04.Game_04_01(inputData.split("\n"),false)
    Day04.Game_04_02(inputData.split("\n"),false) // BAD
}

class Day04 {
    companion object {

    fun Game_04_02(inputLines: List<String>, debug: Boolean) {
        var sum = 0
        var cards = mutableListOf<Int>()
// init
        inputLines.forEachIndexed { row, it ->
            cards.add(row, 1)
        }
        // collect numbers
        inputLines.forEachIndexed { row, it ->
            var numbers = mutableListOf<String>()
            for (i in it.indexOf(':') + 2..it.indexOf('|') - 1 step 3) {
                // println(" -"+ it.substring(i)+ "-")
                // println(" -"+ it.substring(i,i + 2)+ "-")
                numbers.add(it.substring(i, i + 2).trim())
            }
            var found = 0
            for (i in it.indexOf('|') + 2..it.length - 1 step 3) {
                // println(" -"+ it.substring(i)+ "-")
                // println(" -"+ it.substring(i,i + 2)+ "-")
                if (numbers.contains(it.substring(i, i + 2).trim())) found = found + 1

            }

//        println(found+1)
            //      sum = sum + 1
            if (found > 0) {
                for (j in (1..found)) {
                    cards.set((row + j), cards.get(row + j) + 1 * cards.get(row))
                }
            }
            println(cards.get(row))
            sum = sum + cards.get(row)
            //  println(sum)

        }

        println(sum)
    }

    fun Game_04_01(inputLines: List<String>, debug: Boolean) {
        var sum = 0

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
            if (found > -1) sum = sum + Math.pow(2.0, found.toDouble()).toInt()
            println(sum)

        }
        println(sum)
    }


}
}