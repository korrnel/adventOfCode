
fun main(args: Array<String>) {

    val inputData = Common.getData(8)


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
    timing {
        println(Day08.Game_01(inputData1.trim(),true))
    }
    timing {
        println(Day08.Game_01(inputData2.trim(),true))
    }
    timing {
        println(Day08.Game_01(inputData.trim(),true))
    }

}

class Day08 {
    companion object {


    var direction = String
    var nodes = mutableMapOf<String,Pair<String,String>>()
        fun Game_01(inputLines: String, debug: Boolean): Int {
            // init
            var lines = inputLines.split("\n")
            val direction = lines[0]
            println(direction)
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
                if (direction[step]=='L') {postion = nodes.get(postion)!!.first}
                    else {postion = nodes.get(postion)!!.second}

                step++
                steps++

            } while (postion!="ZZZ")

            return  steps
        }

    }
}