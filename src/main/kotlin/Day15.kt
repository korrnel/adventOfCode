fun main(args: Array<String>) {

    val inputData = Common.getData(15)


    var inputData1 =
                        "rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7"
        println( Day15.Game_01(inputData.trim().split(","),false))
        println( Day15.Game_02(inputData.trim().split(","),false))

}

class Day15 {
    companion object {
        fun Game_02(inputLines: List<String>, debug: Boolean) : Int {
            if (debug) println(inputLines)
            var sum = 0
            var boxes =  MutableList(inputLines.size) { MutableList(1) { "" } }

            inputLines.forEach {
                 var item= it.split('=')
                 if (it.contains("-")) {
                     var boxNum =   hashIt(item[0].substring(0,item[0].length-1))
                     var box = boxes.get(boxNum).toMutableList()
                     var rem=-1
                     box.forEachIndexed { index, s ->
                         if (s.startsWith(item[0].substring(0,item[0].length-1))) rem= index
                     }
                     if (rem>-1) box.removeAt(rem)
                     boxes.set(boxNum,box)
                   //if (debug) println(it + " " + item[0].substring(0,item[0].length-1)+ " " + hashIt(item[0].substring(0,item[0].length-1)))
                 } else { //
                   var box = boxes.get(hashIt(item[0]))
                     var rem=-1
                     box.forEachIndexed { index, s ->
                         if (s.startsWith(item[0])) rem= index
                     }
                     if (rem>-1) {
                         box.set(rem,it)
                     } else {
                        box.add(it)
                     }
                   boxes.set(hashIt(item[0]),box)
                 }
                if (debug) println("------------------------------------")
                if (debug) boxes.forEachIndexed { index, strings -> println("Box " + index.toString() + " " + strings.toString()) }
//                        println(it + " "+ hashIt(it).toString())
            }
            if (debug) println("--END END END END --")
            if (debug) boxes.forEachIndexed { index, strings -> println("Box " + index.toString() + " " + strings.toString()) }

            boxes.forEachIndexed { index, strings ->
                strings.forEachIndexed{ slot, string ->
                    if (string.contains("=")) sum = sum + (index + 1) * slot * string.split('=')[1].toInt()
                }
            }

            return sum
        }
        fun Game_01(inputLines: List<String>, debug: Boolean) : Int {
            println(inputLines)
            var sum = 0
            inputLines.forEach {
                sum = sum + hashIt(it)
            }

            return sum
        }
        fun hashIt(input : String) : Int{
            var base = 0
            input.forEach { base = hashOne(it,base) }
            return  base
        }
        fun hashOne(input : Char, base : Int) : Int{
            return ((base + input.code )* 17) % 256
        }
    }
}