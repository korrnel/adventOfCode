
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse


fun main(args: Array<String>){
    val client = HttpClient.newBuilder().build()
    val request = HttpRequest.newBuilder()
        .uri(URI.create("https://adventofcode.com/2023/day/3/input"))
        .header("Cookie","_ga=GA1.2.295804623.1701507354; _gid=GA1.2.131787216.1701507354; session=53616c7465645f5fda3577ccfc12109342e8ec2e219ce4ccdc420e56d526eb4f59b00e396a73b67de8c5c4686db7b47d1bd6343dc6c28fd6fd35bce3818065d4; _ga_MHSNPJKWC7=GS1.2.1701511847.2.0.1701511847.0.0.0")
        .build()
    val response = client.send(request, HttpResponse.BodyHandlers.ofString())

    val inputData = response.body()
    val inputData1 = "two1nine\n" +
            "eightwothree\n" +
            "abcone2threexyz\n" +
            "xtwone3four\n" +
            "4nineeightseven2\n" +
            "zoneight234\n" +
            "7pqrstsixteen"
    val inputData2 ="Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green\n" +
            "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue\n" +
            "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red\n" +
            "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red\n" +
            "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"
    var inputData3 =
            "467..114...\n" +
            "...*......\n" +
            "..35..633.\n" +
            "......#...\n" +
            "617*......\n" +
            ".....+.58.\n" +
            "..592.....\n" +
            "......755.\n" +
            "...\$.*....\n" +
            ".664.598..\n" +
            ""
    val inputLines =  inputData.split("\n")
    Game_03_02(inputLines, true)
}

data class FoundOne(
    val number : String,
    val area : Set<Pair<Int, Int>>
)

fun Game_03_02(inputLines: List<String>, debug : Boolean) {
    var sum = 0
    var numbers = mutableListOf<FoundOne>()
    // collect numbers
    inputLines.forEachIndexed { i, it ->
        var number = ""
        var start = -1
        it.forEachIndexed() { j, it2 ->
            if ((it2.isDigit())) {
                if (start < 0) {
                    start = j
                }
                number = number + it2
            }
            if (!(it2.isDigit()) or (j == it.length - 1)) {
                if (start > -1) {
                    numbers.add(FoundOne(number, getArea(i,j,number)))
                    if (debug) {
                        println("at line" + i + " -- " + number)
                    }
                    number=""
                    start= -1
                }
            }
        }
    }
    // numbers collected
    inputLines.forEachIndexed { i, it ->
        var partialPower = 1
        it.forEachIndexed() { j, it2 ->
            if (it2.equals('*')) {
              val whereIm  = setOf(Pair(i,j))
              println(i.toString() + "-" + j.toString()+ " Near")
                var Found = mutableListOf<Int>()
                numbers.forEach { item ->
                  if(item.area.intersect(whereIm).isNotEmpty()) {
                      Found.add(item.number.toInt())
                      if (debug) {
                          println(item.number + " Has found ")
                      }

                   }
                }
                if (Found.size==2) sum = sum + Found[0]*Found[1]
                if (debug) {
                    println(sum)
                }

            }
        }
        //sum = sum + partialPower
    }
    println(sum)
}

fun getArea(row : Int, column :Int , number : String) : MutableSet<Pair<Int, Int>>
{
    var result = mutableSetOf<Pair<Int, Int>>()
    val delta = 1
    for (j in row-delta..row+delta)
    {
        for (i in column-delta-number.length..column  ){
            result.add(Pair(j,i))
         //   println(j.toString() + " " + i.toString() + "  - "  + number)
        }

    }
    return result
}

fun Game_03(inputLines: List<String>, debug : Boolean) {
    var sum = 0
    var alreadyIn : IntArray = intArrayOf()

    inputLines.forEachIndexed { i, it ->
        var number = ""
        var start = -1
        it.forEachIndexed() { j, it2 ->

                if ((it2.isDigit())) {
                    if (start < 0) {
                        start = j
                    }
                    number = number + it2
                }
               if (!(it2.isDigit()) or (j==it.length-1)) {
                    if (start > -1) {
                        if (debug) {
                            print("at line" + i + " -- " + number)
                        }
                        if (isAPart(i, start, j, inputLines)) {
                            if (debug) {
                                print(" - PART ")
                            }
                            sum = sum + number.toInt()

                        }
                        start = -1
                        number = ""
                        if (debug) {
                            println(" ")
                        }
                    }
                }

        }
        if (debug) {
            println(sum)
        }
    }
    println(sum)
    println(alreadyIn.size)

    // 526868 is bad since it doesn't consider the numbers at the end
    // 528547??
    // 327344
}
fun isAPart(line : Int, colStart: Int, colEnd: Int, lineList : List<String>):Boolean
{

    var startLine = if (line<1) line else line - 1
    var startColumn = if (colStart<1) colStart else colStart-1
    var endColumn = if (lineList[line].length-1==colEnd) colEnd-1 else colEnd
    if (line>0)
    {
        for (i in startColumn..endColumn ){
         if(!((lineList[startLine][i].isDigit()) or (lineList[startLine][i]=='.')))
          { return true }
        }
    }

    for (i in startColumn..endColumn ){
        if(!((lineList[line][i].isDigit()) or (lineList[line][i]=='.')))
        { return true }
    }

    if (lineList.size-2>line) {
        for (i in startColumn..endColumn ){
            if(!((lineList[line+1][i].isDigit()) or (lineList[line+1][i]=='.')))
            { return true }
        }
    }
    return false
}

object Balls {
    var blue = 0
    var red = 0
    var green = 0
}

fun Game_02_2(inputLines: List<String>, debug : Boolean) {
    var sum = 0
    var game = 0
    var minBall = Balls

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
    inputLines.forEach ({ it ->
        var possible = true
        game = game + 1
        val games = it.split(";")
        games.forEach({ it2 ->
           if (!PossibleGame(it2)) { possible= false }
            })
        if (possible) {
            sum = sum + game
        }
        if (debug) {
            println(possible)
            println(it)
        }

    })
   println(sum-game)
}

fun PossibleGame(it : String) : Boolean {
// limit only 12 red cubes, 13 green cubes, and 14 blue
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

fun Game_01_2(inputLines: List<String>, debug : Boolean) {
var sum = 0

    inputLines.forEach({it ->
        sum = sum + firstNum(it) * 10  + lastNum(it)
     if (debug) {
            print(firstNum(it))
            println(lastNum(it))
            println(it)
     }
    })
    println(sum)
}


fun firstNum(i: String) : Int
{
    var s= i
// added for second scenario - begin
    s = s.replace("two","t2o").replace("one","o1e")
    s = s.replace("three","t3e").replace("four","f4r")
    s = s.replace("five","f5e").replace("six","s6x")
    s = s.replace("seven","s7n").replace("eight","e8t").replace("nine","n9e")

// added for second scenario - end

    s.forEach { it ->
        if (it.isDigit()) {
            return it.digitToInt()
        }
      }
    return 0

}

fun lastNum(sInput: String) : Int
{
    var s= sInput

// added for second scenario - begin
    s = s.replace("two","t2o").replace("one","o1e")
    s = s.replace("three","t3e").replace("four","f4r")
    s = s.replace("five","f5e").replace("six","s6x")
    s = s.replace("seven","s7n").replace("eight","e8t").replace("nine","n9e")

// added for second scenario - end

    for (i in (0..s.length-1).reversed()) {
        if (s[i].isDigit()) {
            return s[i].digitToInt()
        }
    }
    return 0

}