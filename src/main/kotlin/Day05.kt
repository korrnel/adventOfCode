
import java.util.PriorityQueue

// to check the processing time
fun <T>timing(block: () -> T) {
    val start = System.currentTimeMillis()
    try {
        block.invoke()
    } finally {
        val end = System.currentTimeMillis()
        println("Took ${end - start} ms.")
    }
}
fun main(args: Array<String>) {

    val inputData = Common.getData(5, args[0])


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
 // for first seeds mapped, to locations and the closest location is needed to be found

    timing {
        println(Day05.Game_01(inputData1.split("\n"),false))
    }
    // in the second the same but the input is not seed numbers but seed ranges , so more seeds to be examined
    timing {
        println(Day05.Game_02(inputData1.split("\n"),false))
    }
    timing {
        println(Day05.Game_02_alt(inputData1.split("\n"),false))
    }
    timing {
        println(Day05.Game_02_alt2(inputData1.split("\n"),false))
    }
    timing {
        println(Day05.Game_02_reverse(inputData1.split("\n"),false))
    }
    timing {
        println(Day05.Game_01(inputData.split("\n"),false))
    }
    timing {
         println(Day05.Game_02_reverse(inputData.split("\n"),false))
    }
}

class Day05 {
    companion object {



        fun getRange(from: String, to: String, inputLines: List<String>):List<Pair<LongRange,LongRange>> {
            var range = mutableListOf<Pair<LongRange,LongRange>>()
            for (i in inputLines.indexOf(from) + 1..inputLines.indexOf(to) - 2) {
                val range1 = (inputLines.get(i).split(" "))
                //          50 98 2
                //          52 50 48
                range.add(Pair(range1[1].toLong()..range1[1].toLong() + range1[2].toLong()-1 ,
                range1[0].toLong()..range1[0].toLong() + range1[2].toLong()-1))
            }
            // order by second since doin backwards at the final solution
            return range.sortedWith( compareBy { it.second.first })
        }
        fun getRangeLast(from: String,inputLines: List<String>):List<Pair<LongRange,LongRange>> {
            var range = mutableListOf<Pair<LongRange,LongRange>>()

            for (i in inputLines.indexOf(from) + 1..inputLines.size-2) {
                val range1 = (inputLines.get(i).split(" "))
                //           50 98 2
                //          52 50 48
                range.add(Pair(range1[1].toLong()..range1[1].toLong() + range1[2].toLong(),
                        range1[0].toLong()..range1[0].toLong() + range1[2].toLong()))
            }
            return range
        }
        fun mapIt(Range1:List<Pair<LongRange,LongRange>>,input : Long): Long{
            Range1.forEachIndexed { index, it ->
                if (input in it.first) {
                    return Range1[index].second.elementAt(it.first.indexOf(input))
                }
            }
            return input
        }
        fun process2(inp : LongRange): List<LongRange>{
            var result = listOf(inp)
            for (i in 0 until ranges.size)
            {
                result = mapIt2(i,result)
            }
            return result

        }

        fun intersectRanges(range1: LongRange, range2: LongRange): LongRange? {
            val start = maxOf(range1.first, range2.first)
            val end = minOf(range1.last, range2.last)

            return if (start <= end) {
                start..end
            } else {
                null // Ranges do not intersect
            }
        }

        fun mapIt2(range: Int, inp: List<LongRange>): List<LongRange>{
            var next = mutableListOf<LongRange>()
            var queue = PriorityQueue<LongRange>( compareBy { it.first })
            // add all to the need to process
            inp.all { queue.add(it) }
            while (queue.isNotEmpty()) {
                // try it
                val i = queue.poll()
                var match = false
                // check the ranges
                ranges[range].forEachIndexed { index, it ->
                    val intersection = intersectRanges(it.first,i)
                    // there's a match
                    if (intersection != null) {
                            match = true
                            if (i.first< intersection.min()) {
                                // this needed to be processed
                                queue.add(i.first..intersection.min()-1)
                            }
                            if (i.last< intersection.max()) {
                                // this needed to be processed
                                queue.add(intersection.max()+1..i.last)
                            }
                            // it is matched so map it!!
                            var mapped =
                                it.second.elementAt(
                                    it.first.indexOf(intersection.min()))..
                                        it.second.elementAt(
                                            it.first.indexOf(intersection.max()))
                            next.add(mapped)
                            // println(intersection.toString() + " - " + mapped.toString())

                    }
                }
                // if no match return the original
                if (!match) next += i
            }
            return next
        }


        lateinit var ranges : List<List<Pair<LongRange,LongRange>>>
        fun Game_02_alt2(inputLines: List<String>,debug: Boolean):Long {
            var locations = mutableListOf<Long>()
            // get maps
            init(inputLines)

            val seeds = inputLines[0].split("seeds: ")[1].run { split(" ").map { it.toLong() } }


            var seedRanges = mutableListOf<LongRange>()
            for (k in 0..seeds.size - 1 step (2))  // it takes forever :(
            {
                seedRanges.add(seeds[k]..seeds[k] + seeds[k + 1] - 1)
            }
            // process
            val results = seedRanges.map {
                process2(it)
            }.flatten()
            locations.addAll(results.flatten())
            /*
            seedRanges.forEach {
                locations.addAll(process2(it).flatten())
            }*/
            println(locations.distinct().sorted())
            return locations.min()
        }

        private fun init(inputLines: List<String>) {
            val ranges1 = mutableListOf<List<Pair<LongRange,LongRange>>>()


            ranges1.add(getRange("seed-to-soil map:", "soil-to-fertilizer map:", inputLines))
            ranges1.add(getRange("soil-to-fertilizer map:", "fertilizer-to-water map:", inputLines))
            ranges1.add(getRange("fertilizer-to-water map:", "water-to-light map:", inputLines))
            ranges1.add(getRange("water-to-light map:", "light-to-temperature map:", inputLines))
            ranges1.add(getRange("light-to-temperature map:", "temperature-to-humidity map:", inputLines))
            ranges1.add(getRange("temperature-to-humidity map:", "humidity-to-location map:", inputLines))
            ranges1.add(getRangeLast("humidity-to-location map:", inputLines))

            ranges = ranges1

        }

        fun Game_02_alt(inputLines: List<String>,debug: Boolean):Long {
            var locations = mutableListOf<Long>()
            // get maps
            val Range1 = getRange("seed-to-soil map:", "soil-to-fertilizer map:", inputLines)
            val Range2 = getRange("soil-to-fertilizer map:", "fertilizer-to-water map:", inputLines)
            val Range3 = getRange("fertilizer-to-water map:", "water-to-light map:", inputLines)
            val Range4 = getRange("water-to-light map:", "light-to-temperature map:", inputLines)
            val Range5 = getRange("light-to-temperature map:", "temperature-to-humidity map:", inputLines)
            val Range6 = getRange("temperature-to-humidity map:", "humidity-to-location map:", inputLines)
            val Range7 = getRangeLast("humidity-to-location map:", inputLines)

            val seeds = inputLines[0].split("seeds: ")[1].run { split(" ").map { it.toLong() } }

            for (k in 0..seeds.size - 1 step (2))  // it takes forever :(
            {
                for (j in 0..seeds[k + 1] - 1) {
                    var i =mapIt(Range1, seeds[k] + j)
                    i = mapIt(Range2, i)
                    i = mapIt(Range3, i)
                    i = mapIt(Range4, i)
                    i = mapIt(Range5, i)
                    i = mapIt(Range6, i)
                    i = mapIt(Range7, i)
                    locations.add(i)
                  //  println(i)
                }
            }
            println(locations.distinct().sorted())
            return locations.min()
        }
        fun Game_02(inputLines: List<String>, debug: Boolean): Long {
            var sum = 0
            var locations = mutableListOf<Long>()
            val seeds = inputLines[0].split("seeds: ")[1].run { split(" ").map { it.toLong() } }
            for (k in 0..seeds.size - 1 step (2))  // it takes forever :(
            {
                if (debug) println(seeds[k].toString())
                if (debug) println(seeds[k + 1].toString())
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
            return locations.min()
        }


    fun Game_01(inputLines: List<String>, debug: Boolean) : Long {
        var sum = 0
        // collect the seeds
        val seeds = inputLines[0].split("seeds: ")[1].run { split(" ").map { it.toLong() } }

//    var seedToSoil = mapper ("seed-to-soil map:" ,"soil-to-fertilizer map:",inputLines)

        // go trough all the mappings
        var locations = mutableListOf<Long>()
        seeds.forEach {
            var i = mapperAlt("seed-to-soil map:", "soil-to-fertilizer map:", inputLines, it)
            if (debug) print("$it corresponds to - " + i.toString())
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

        // find the closest  location where a seed can be planted
        return locations.min()
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

        fun Game_02_reverse(inputLines: List<String>, debug: Boolean): Long {
            init(inputLines)

            val seeds = inputLines[0].split("seeds: ")[1].run { split(" ").map { it.toLong() } }
            var seedRanges = mutableListOf<LongRange>()
            for (k in 0..seeds.size - 1 step (2))  // it takes forever :(
            {
                seedRanges.add(seeds[k]..seeds[k] + seeds[k + 1] - 1)
            }
            seedRanges = seedRanges.sortedBy { it.first }.toMutableList()
            if (walkBackwards(46L,seedRanges)) return 46L

            (0L until 20000000L).forEach {
                //println(it)
                if (walkBackwards(it,seedRanges)) return it
            }

         return -1

        }

        fun walkBackwards(i : Long,seedRanges: List<LongRange>) :Boolean{
            var inp= i
            for (i in (0 until ranges.size).reversed())
            {
                inp= mapItBackward(ranges[i],inp)
            }

            for (seedRange in seedRanges){
                if (inp in seedRange) {
                    return true
                }
            }
            return  false
        }




        fun mapItBackward(range: List<Pair<LongRange,LongRange>>, inp : Long): Long{
            var ret= inp

            //if (range.none{ it.second.contains(inp) }) return ret

            range.forEach {
                if (inp>=it.second.first && inp <= it.second.last )
                //if (it.second.contains(inp))
                //if (inp > it.second.first && inp < it.second.last)
                //if (inp in it.second)
                    return it.first.first + (inp - it.second.first)  // fuck the fancy functions....
                   //return  it.first.elementAt(it.second.indexOf(ret))
            //
            //    return  it.first.elementAt(it.second.indexOf(ret))
            }
            return ret
        }
    }
}