fun main(args: Array<String>) {

        val inputData = Common.getData(1, args[0])

        val inputData1 = "1abc2\n" +
                "pqr3stu8vwx\n" +
                "a1b2c3d4e5f\n" +
                "treb7uchet"
        val inputData2 = "two1nine\n" +
                "eightwothree\n" +
                "abcone2threexyz\n" +
                "xtwone3four\n" +
                "4nineeightseven2\n" +
                "zoneight234\n" +
                "7pqrstsixteen\n"
    println(Day01.Game_01_01(inputData1.split("\n"),false))
    println(Day01.Game_01_02(inputData2.split("\n"),false))

    println(Day01.Game_01_01(inputData.split("\n"),false))
    println(Day01.Game_01_02(inputData.split("\n"),false))
    }
class Day01 {
    companion object {
    fun Game_01_01(inputLines: List<String>, debug : Boolean):Int {
        var sum = 0
        // find the first and last numbers in the line
        inputLines.forEach {it ->
            // sum them up
            sum = sum + firstNum1(it) * 10  + lastNum1(it)
            if (debug) {
                println(firstNum1(it)*10+lastNum1(it))
            }
        }

        return sum
    }
        fun Game_01_02(inputLines: List<String>, debug: Boolean): Int {
            var sum = 0
            // first and last numbers, but they can be in written format also
            inputLines.forEach { it ->
                // sum them up
                sum = sum + firstNum(it) * 10 + lastNum(it)
                if (debug) {
                    println(firstNum(it)*10+lastNum(it))
                    //println(it)
                }
            }
            return sum
        }


        fun firstNum(i: String): Int {
            var s = i
// added for second scenario - begin
            s = s.replace("two", "t2o").replace("one", "o1e")
            s = s.replace("three", "t3e").replace("four", "f4r")
            s = s.replace("five", "f5e").replace("six", "s6x")
            s = s.replace("seven", "s7n").replace("eight", "e8t").replace("nine", "n9e")

// added for second scenario - end

            s.forEach { it ->
                if (it.isDigit()) {
                    return it.digitToInt()
                }
            }
            return 0

        }

        fun lastNum(sInput: String): Int {
            var s = sInput

// added for second scenario - begin
            s = s.replace("two", "t2o").replace("one", "o1e")
            s = s.replace("three", "t3e").replace("four", "f4r")
            s = s.replace("five", "f5e").replace("six", "s6x")
            s = s.replace("seven", "s7n").replace("eight", "e8t").replace("nine", "n9e")

// added for second scenario - end

            for (i in (0..s.length - 1).reversed()) {
                if (s[i].isDigit()) {
                    return s[i].digitToInt()
                }
            }
            return 0

        }

        fun firstNum1(i: String): Int {
            var s = i
            s.forEach { it ->
                if (it.isDigit()) {
                    return it.digitToInt()
                }
            }
            return 0

        }

        fun lastNum1(sInput: String): Int {
            var s = sInput

            for (i in (0..s.length - 1).reversed()) {
                if (s[i].isDigit()) {
                    return s[i].digitToInt()
                }
            }
            return 0

        }


    }
}