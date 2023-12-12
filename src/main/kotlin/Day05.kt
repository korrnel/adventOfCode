fun main(args: Array<String>) {

    val inputData = Common.getData(5)


    var inputData1 = "seeds: 79 14 55 13\n" +
            "\n" +
            "seed-to-soil map:\n" +
            "50 98 2\n" +
            "52 50 48\n" +
            "\n" +
            "soil-to-fertilizer map:\n" +
            "0 15 37\n" +
            "37 52 2\n" +
            "39 0 15\n" +
            "\n" +
            "fertilizer-to-water map:\n" +
            "49 53 8\n" +
            "0 11 42\n" +
            "42 0 7\n" +
            "57 7 4\n" +
            "\n" +
            "water-to-light map:\n" +
            "88 18 7\n" +
            "18 25 70\n" +
            "\n" +
            "light-to-temperature map:\n" +
            "45 77 23\n" +
            "81 45 19\n" +
            "68 64 13\n" +
            "\n" +
            "temperature-to-humidity map:\n" +
            "0 69 1\n" +
            "1 0 69\n" +
            "\n" +
            "humidity-to-location map:\n" +
            "60 56 37\n" +
            "56 93 4"

    Day05.Game_05_01(inputData1.split("\n"),false)
    Day05.Game_05_02(inputData1.split("\n"),false) // BAD

    Day05.Game_05_01(inputData.split("\n"),false)
    Day05.Game_05_02(inputData.split("\n"),false) // BAD
}

class Day05 {
    companion object {


        fun Game_05_02(inputLines: List<String>, debug: Boolean) {
        var sum = 0
        var locations = mutableListOf<Long>()
        val seeds = inputLines[0].split("seeds: ")[1].run { split(" ").map { it.toLong() } }
        for (k in 0..seeds.size - 1 step (2))  // it takes forever :(
        {
            println(seeds[k].toString())
            println(seeds[k + 1].toString())
            for (j in 0..seeds[k + 1] - 1) {

                var i = mapperAlt("seed-to-soil map:", "soil-to-fertilizer map:", inputLines, seeds[k] + j)
                if (debug) print((seeds[k] + j).toString() + " corresponds to - " + i.toString())
                i = mapperAlt("soil-to-fertilizer map:", "fertilizer-to-water map:", inputLines, i)
                if (debug) print(" - " + i.toString() + " - ")
                i = mapperAlt("fertilizer-to-water map:", "water-to-light map:", inputLines, i)
                if (debug) print(i.toString() + " - ")
                i = mapperAlt("water-to-light map:", "light-to-temperature map:", inputLines, i)
                if (debug) print(i.toString() + " - ")
                i = mapperAlt("light-to-temperature map:", "temperature-to-humidity map:", inputLines, i)
                if (debug) print(i.toString() + " - ")
                i = mapperAlt("temperature-to-humidity map:", "humidity-to-location map:", inputLines, i)
                if (debug) print(i.toString() + " - ")
                i = mapperAltLast("humidity-to-location map:", inputLines, i)
                locations.add(i)
                if (debug) println(i.toString())

            }
        }
//    var seedToSoil = mapper ("seed-to-soil map:" ,"soil-to-fertilizer map:",inputLines)
        println(min(locations))
    }


    fun Game_05_01(inputLines: List<String>, debug: Boolean) {
        var sum = 0
        val seeds = inputLines[0].split("seeds: ")[1].run { split(" ").map { it.toLong() } }

//    var seedToSoil = mapper ("seed-to-soil map:" ,"soil-to-fertilizer map:",inputLines)

        var locations = mutableListOf<Long>()
        seeds.forEach({
            var i = mapperAlt("seed-to-soil map:", "soil-to-fertilizer map:", inputLines, it)
            print("$it corresponds to - " + i.toString())
            i = mapperAlt("soil-to-fertilizer map:", "fertilizer-to-water map:", inputLines, i)
            print(" - " + i.toString() + " - ")
            i = mapperAlt("fertilizer-to-water map:", "water-to-light map:", inputLines, i)
            print(i.toString() + " - ")
            i = mapperAlt("water-to-light map:", "light-to-temperature map:", inputLines, i)
            print(i.toString() + " - ")
            i = mapperAlt("light-to-temperature map:", "temperature-to-humidity map:", inputLines, i)
            print(i.toString() + " - ")
            i = mapperAlt("temperature-to-humidity map:", "humidity-to-location map:", inputLines, i)
            print(i.toString() + " - ")
            i = mapperAltLast("humidity-to-location map:", inputLines, i)
            locations.add(i)
            println(i.toString())
        })

        println(min(locations))
    }

    fun min(locations: MutableList<Long>): String {
        var i = Long.MAX_VALUE
        locations.forEach { it ->
            if (it < i) {
                i = it
            }
        }
        return i.toString()
    }

    fun mapperAlt(from: String, to: String, inputLines: List<String>, inp: Long): Long {

        for (i in inputLines.indexOf(from) + 1..inputLines.indexOf(to) - 2) {
            val range1 = (inputLines.get(i).split(" "))
            // println(inputLines.get(i).split(" "))
            if ((inp - range1[1].toLong()) >= 0 && (inp - range1[1].toLong() <= range1[2].toLong())) {
                return range1[0].toLong() + inp - range1[1].toLong()
            }
            //    for (j in 0..range1[2].toLong()-1) {
            //        if (inp == (range1[1].toLong() + j)) return (range1[0].toLong() + j)
            //    }

        }
        return inp
    }

    fun mapperAltLast(from: String, inputLines: List<String>, inp: Long): Long {
        //var seedToSoil = mutableMapOf<Long,Long>()
        for (i in inputLines.indexOf(from) + 1..inputLines.size - 2) {
            val range1 = (inputLines.get(i).split(" "))
            if ((inp - range1[1].toLong()) > 0 && (inp - range1[1].toLong() < range1[2].toLong())) {
                return range1[0].toLong() + inp - range1[1].toLong()
            }

//        for (j in 0..range1[2].toLong()-1) {
//            if (inp==range1[1].toLong()+j) return range1[0].toLong()+j
            //       }
            //println(inputLines.get(i).split(" "))
        }
        return inp
    }

}
}