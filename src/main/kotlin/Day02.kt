fun main(args: Array<String>) {

    val inputData = Common.getData(2, args[0])


    val inputData1 ="Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green\n" +
            "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue\n" +
            "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red\n" +
            "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red\n" +
            "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"
    println(Day02.Game_02(inputData1.split("\n"),false))
    println(Day02.Game_02_2(inputData1.split("\n"),false))

    println(Day02.Game_02(inputData.split("\n"),false))
    println(Day02.Game_02_2(inputData.split("\n"),false))
}
class Day02 {
    companion object {

        object Balls {
            var blue = 0
            var red = 0
            var green = 0
        }

        fun Game_02_2(inputLines: List<String>, debug : Boolean) {
            var sum = 0
            var game = 0
            var minBall = Balls
        // this time find out the min of balls needed, for the game (multiple scenarios are in a line/game)
            inputLines.forEach { it ->
                val games = it.split(";")
                minBall.blue = 0
                minBall.red = 0
                minBall.green = 0
                games.forEach { it2 ->
                    minBall = minBalls(minBall, it2)
                }
                if (debug) {
                    println(minBall.blue * minBall.green * minBall.red)
                    println(minBall.blue.toString() + " " + minBall.green.toString() + " " + minBall.red.toString())
                    println(it)
                }
                // and the min balls will be the target of the sum
                sum = sum + minBall.blue * minBall.green * minBall.red

            }
            println(sum)
        }

        fun minBalls(minBall : Balls, it: String ): Balls{
            var inBalls = minBall
            val red = findCube2(it, "red")
            val green =  findCube2(it, "green")
            val blue =  findCube2(it, "blue")

            if (inBalls.red < red ) inBalls.red=red
            if (inBalls.green < green ) inBalls.green=green
            if (inBalls.blue < blue ) inBalls.blue=blue

            return inBalls
        }


        fun findCube2(it: String, color: String): Int{
            val blue = Regex("""(\d*) $color""").find(it)?.value?.split(" ")?.get(0)
            var blueI= 0
            if (!(blue.isNullOrEmpty())) {
                blueI = blue.toInt()
                return blueI
            }
            return 0

        }

        fun Game_02(inputLines: List<String>, debug : Boolean) {
            var sum = 0
            var game = 0
            inputLines.forEach { it ->
                var possible = true
                game += 1
                val games = it.split(";")
                games.forEach { it2 ->
                    // valid scenario?
                    if (!PossibleGame(it2)) {
                        possible = false
                    }
                }
                // this game is possible? then count as possible
                if (possible) {
                    sum += game
                }
                if (debug) {
                    println(possible)
                    println(it)
                }

            }
            // the last game is added twice
            println(sum-game)
        }

        fun PossibleGame(it : String) : Boolean {
            //  only 12 red cubes, 13 green cubes, and 14 blue are valid games, if there's more drop it
            if (findCube(it, "red", 12)
                and findCube(it, "green", 13)
                and findCube(it, "blue", 14)
            ) {
                return true
            }
            return false
        }

        fun findCube(it: String, color: String, limit: Int): Boolean{
            val blue = Regex("""(\d*) $color""").find(it)?.value?.split(" ")?.get(0)
            var blueI= 0
            if (!(blue.isNullOrEmpty())) {
                blueI = blue.toInt()
            }
            if (blueI <= limit) { return true}
            return false

        }

    }
}