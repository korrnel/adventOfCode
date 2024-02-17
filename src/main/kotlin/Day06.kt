
fun main(args: Array<String>) {

    val inputData = Common.getData(6, args[0])


    var inputData1 = "Time:      7  15   30\n" +
            "Distance:  9  40  200"
// race first 3 race , second concat the numbers and one race
    timing {
        println(Day06.Game_01(inputData1.trim(),true))
    }
    timing {
        println(Day06.Game_01(inputData.trim(),true))
    }
    timing {
        println(Day06.Game_02(inputData1.trim(),true))
    }
    timing {
        println(Day06.Game_02(inputData.trim(),true))
    }
}

class Day06 {
    companion object {
        fun Game_02(inputLines: String, debug: Boolean): Long {
            val races = inputLines.split("\n").map { it.split(":")[1].filter{ !it.isWhitespace() }}.map { it.toLong() }
            if (debug) println(races)


            var locations = mutableListOf<Int>()

                var sum = 0
                for ( j in 1 .. races[0]-1){
                    if (winTheRace(j,races[0],races[1])) sum++
                }
                locations.add(sum)
            println(locations)
            return multiplicate(locations)
        }

        fun Game_01(inputLines: String, debug: Boolean): Long {
            val races = inputLines.split("\n").map { it.split(":")[1].split(' ').filter { it.isNotEmpty()&&it.isNotBlank() }.map { it.toLong() } }
            if (debug) println(races)


            var locations = mutableListOf<Int>()

            for (i in 0 until races[0].size ) {
                var sum = 0
                for ( j in 1 .. races[0][i]-1){
                    if (winTheRace(j,races[0][i],races[1][i])) sum++
                }
                locations.add(sum)
            }
            println(locations)
            return multiplicate(locations)
        }

        fun winTheRace(pushed: Long, maxTime: Long, distance: Long): Boolean{
          //  if ((distance==200)&&((maxTime-pushed) * pushed >= distance)) println(pushed.toString() + " " + (maxTime-pushed) * pushed)
            return (maxTime-pushed) * pushed >= distance
        }

        fun multiplicate( locations : List<Int>): Long {
            var sum = 1L
            for (i in 0 until locations.size) sum *= locations[i]
            return sum
        }

    }
}