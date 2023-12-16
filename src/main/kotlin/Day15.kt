fun main(args: Array<String>) {

    val inputData = Common.getData(15)


    var inputData1 =
                        "rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7"
        println( Day15.Game_01(inputData.trim().split(","),false))

}

class Day15 {
    companion object {
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