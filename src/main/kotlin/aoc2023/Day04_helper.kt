package aoc2023/*
* from here https://github.com/Jadarma/advent-of-code-kotlin-solutions/blob/main/solutions/aockt/y2023/Y2023D04.kt
* but it gives the same....
* */
data class ScratchCard(val id: Int, val haveNumbers: Set<Int>, val winNumbers: Set<Int>) {

    val numberOfWins: Int by lazy {
        haveNumbers.intersect(winNumbers).size
    }

    val score: Long
        get() {
            val n = numberOfWins
            if (n == 0) return 0L
            var res = 1L
            repeat(n - 1) {
                res *= 2
            }
            return res
        }
}

fun main(args: Array<String>) {
    var data = mutableListOf<ScratchCard>()
    val data2 = Common.getData(4, args[0])
    data2.trim().split("\n").forEach{ line ->
        val line2 = line.replace("  "," ")
        val (card, contents) = line2.split(":")
        val (win, have) = contents.split("|")
        val cardId = card.trim().split(" ").mapNotNull { it.toIntOrNull() }.single()
        val haveNumbers = have.trim().split(" ").map { it.toInt() }.toSet()
        val winningNumbers = win.trim().split(" ").map { it.toInt() }.toSet()
        data.add(ScratchCard(cardId, haveNumbers, winningNumbers))
    }

    // Part 1
    println(data.sumOf { it.score })

    // Part 2
    println(process(data, 0, data.size))
}

private fun process(cards: List<ScratchCard>, start: Int, end: Int): Int {
    var ans = end - start
    for (i in start until end) {
        val card = cards[i]
        val wins = card.numberOfWins
        ans += process(cards, i + 1, i + 1 + wins)
    }
    return ans
}