
fun main(args: Array<String>) {

    val inputData = Common.getData(8, args[0])


    var inputData1 = "RL\n" +
            "\n" +
            "AAA = (BBB, CCC)\n" +
            "BBB = (DDD, EEE)\n" +
            "CCC = (ZZZ, GGG)\n" +
            "DDD = (DDD, DDD)\n" +
            "EEE = (EEE, EEE)\n" +
            "GGG = (GGG, GGG)\n" +
            "ZZZ = (ZZZ, ZZZ)"
    var inputData2= "LLR\n" +
            "\n" +
            "AAA = (BBB, BBB)\n" +
            "BBB = (AAA, ZZZ)\n" +
            "ZZZ = (ZZZ, ZZZ)"
    var inputData3 = "LR\n" +
            "\n" +
            "11A = (11B, XXX)\n" +
            "11B = (XXX, 11Z)\n" +
            "11Z = (11B, XXX)\n" +
            "22A = (22B, XXX)\n" +
            "22B = (22C, 22C)\n" +
            "22C = (22Z, 22Z)\n" +
            "22Z = (22B, 22B)\n" +
            "XXX = (XXX, XXX)"
    timing {
        println(Day08.Game_01(inputData1.trim(),false))
    }
    timing {
        println(Day08.Game_01(inputData2.trim(),false))
    }
    timing {
        println("first solution " + Day08.Game_01(inputData.trim(),false).toString())
    }
    timing {
        println(Day08.Game_02(inputData3.trim(),true))
    }
    timing {
        println(Day08.Game_02(inputData.trim(),true))
    }

}

class Day08 {
    companion object {


    var nodes = mutableMapOf<String,Pair<String,String>>()
        fun Game_01(inputLines: String, debug: Boolean): Int {
            // init
            var lines = inputLines.split("\n")
            val direction = lines[0]
            if (debug) println(direction)
            val nodeRegex = Regex("""^(\w{3}) = \((\w{3}), (\w{3})\)$""")
            lines = lines.drop(2)
            lines.forEach {
                val (node, left, right) = nodeRegex.matchEntire(it)!!.destructured
                nodes.put(node,Pair(left,right))
            }
            // start the wandering
            var postion= "AAA"
            var steps = 0
            var step= 0
            do {
                if (step>=direction.length) step=0

                // left or right node to follow?
                postion = if (direction[step]=='L') {
                    nodes.get(postion)!!.first
                } else {
                    nodes.get(postion)!!.second
                }

                step++
                steps++
                // leave if finally on the ZZZ
            } while (postion!="ZZZ")

            return  steps
        }
        fun Game_02(inputLines: String, debug: Boolean): Long {
            // init
            var lines = inputLines.split("\n")
            val direction = lines[0]
            if (debug) println(direction)
            val nodeRegex = Regex("""^(\w{3}) = \((\w{3}), (\w{3})\)$""")
            lines = lines.drop(2)
            var positions= mutableListOf<String>()
            lines.forEach {
                val (node, left, right) = nodeRegex.matchEntire(it)!!.destructured
                nodes.put(node,Pair(left,right))
                if (node.endsWith('A')) positions.add(node)
            }
            // each wondering repeates from A to Z and Z to A and so on...
            var cycles = mutableListOf<Int>()
            // start the wandering all the points ending with A
            positions.forEach { it->
             cycles.add(navigateGhost(it,direction))
            }
            if (debug) println(cycles)
            // so find where they will meet?
            return findLeastCommonMultiple(cycles)
        }
        fun navigateGhost(inputPosition: String,direction : String):Int{
            var postion= inputPosition
            var steps = 0
            var step = 0
            var count = 0
            // println(inputPosition)
            do {
                if (step >= direction.length) step=0
                if (direction[step]=='L') {postion = nodes.get(postion)!!.first}
                else {postion = nodes.get(postion)!!.second}
                step++
                steps++

                // we have arrived
                if (postion.endsWith('Z') ) {
              //      println(steps)
                    count++
                }
            } while (count<1)
            //println(steps)
            return steps
        }
        fun findLeastCommonMultiple(numbers : List<Int>): Long {
            val larger = numbers.max().toLong()

            var maxLcm = 1L
            numbers.forEach { maxLcm *= it }

            var lcm = larger
            while (lcm <= maxLcm) {
                if (divide(lcm,numbers)) {
                    return lcm
                }
                lcm += larger
            }
            return maxLcm
        }
        fun divide(lcm: Long, numbers: List<Int>) : Boolean {
            var fraction = 0L
            numbers.forEach { fraction += lcm % it }
            return  fraction<1
        }
    }
}