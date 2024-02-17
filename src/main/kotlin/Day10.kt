fun main(args: Array<String>) {

    val inputData = Common.getData(10, args[0])


    var inputData1 =
        "..F7.\n" +
    ".FJ|.\n" +
            "SJ.L7\n" +
            "|F--J\n" +
            "LJ..."
   // Day10.Game_10_01(inputData1.split("\n"),false)

    Day10.Game_10_01(inputData.split("\n"),false)
}

class Day10 {
    companion object {

    fun Game_10_01(inputLines: List<String>, debug: Boolean) {
        var sum: Int = 0
        var positionX: Int
        var positionY: Int

        val pos = findS(inputLines)
        positionY = pos[0]
        positionX = pos[1]


        var StartPositionX = positionX
        var StartPositionY = positionY
        println(pos)

        // first move
        positionY = pos[0] - 1
        positionX = pos[1]
        var direction: Int = 1
        /*
         1 up
         2 down
         3 left
         4 right
         */
//    println(inputLines)
        while (!(StartPositionX == positionX && StartPositionY == positionY) && sum < 50000000) {
            sum = sum + 1
            //   println(inputLines[positionY][positionX])
            when (inputLines[positionY][positionX]) {
                '|' -> {
                    when (direction) {
                        1 -> positionY = positionY - 1
                        2 -> positionY = positionY + 1
                    }
                }

                '-' -> {
                    when (direction) {
                        3 -> positionX = positionX - 1
                        4 -> positionX = positionX + 1
                    }
                }

                'L' -> {
                    when (direction) {
                        2 -> {
                            positionX = positionX + 1
                            direction = 4
                        }

                        3 -> {
                            positionY = positionY - 1
                            direction = 1
                        }
                    }
                }

                'J' -> {
                    when (direction) {
                        2 -> {
                            positionX = positionX - 1
                            direction = 3
                        }

                        4 -> {
                            positionY = positionY - 1
                            direction = 1
                        }
                    }
                }

                'F' -> {
                    when (direction) {
                        1 -> {
                            positionX = positionX + 1
                            direction = 4
                        }

                        3 -> {
                            positionY = positionY + 1
                            direction = 2
                        }
                    }
                }

                '7' -> {
                    when (direction) {
                        1 -> {
                            positionX = positionX - 1
                            direction = 3
                        }

                        4 -> {
                            positionY = positionY + 1
                            direction = 2
                        }
                    }
                }
            }
            println(sum)
        }

        println((sum + 1) / 2)
    }

    fun findS(inputLines: List<String>): List<Int> {
        var result = mutableListOf<Int>()
        inputLines.forEachIndexed({ y, it ->
            it.forEachIndexed({ x, it2 ->
                if (it2.equals('S')) {
                    result.add(0, y)
                    result.add(1, x)
                    return result
                }
            })
        })
        return result
    }

}
}