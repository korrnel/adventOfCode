
import org.jetbrains.kotlin.js.translate.utils.splitToRanges
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse


fun main(args: Array<String>){
    val client = HttpClient.newBuilder().build()
    val request = HttpRequest.newBuilder()
        .uri(URI.create("https://adventofcode.com/2023/day/9/input"))
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
    var inputData4= "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53\n" +
            "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19\n" +
            "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1\n" +
            "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83\n" +
            "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36\n" +
            "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"
    var inputData5= "seeds: 79 14 55 13\n" +
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
    val inputData7 = "32T3K 765\n" +
                 "T55J5 684\n" +
                 "KK677 28\n" +
                 "KTJJT 220\n" +
                 "QQQJA 483"
    val inputData9 = "0 3 6 9 12 15\n" +
            "1 3 6 10 15 21\n" +
            "10 13 16 21 30 45"
    val inputLines =  inputData.trim().split("\n")
    Game_09_02(inputLines, true)
}


fun Game_09_02(inputLines: List<String>, debug : Boolean) {
    var sum : Long = 0
    inputLines.forEach({it ->
        val numbers = it.split(" ").map { it.toLong() }
        for (i in 0..numbers.size-1)
        {
            if (debug)          print(" " +(numbers.get(i)))

        }
        if (debug)      println()
        val x = moveUp(numbers)
        if (debug)     println(x)
        sum=sum + numbers.first()+x*-1
        println(sum.toString() + " - " + numbers.first()+ " " + x.toString())
    })
    println(sum)
}

fun moveUp(numbers : List<Long>): Long{
    var numbers2 = mutableListOf<Long>()
    var max: Long =0
    for (i in 1..numbers.size-1)
    {
        numbers2.add(numbers.get(i)-numbers.get(i-1))
        print(" " + (numbers.get(i)-numbers.get(i-1)))
    }
    println()
    if (numbers2.min()!=0L || numbers2.max()!=0L) {
        max = moveUp(numbers2)
    }
    // println(max.toString() + " <---")
    return (numbers2.first()+max*-1)
}
fun Game_09_01(inputLines: List<String>, debug : Boolean) {
    var sum : Long = 0
    inputLines.forEach({it ->
        val numbers = it.split(" ").map { it.toLong() }
        for (i in 0..numbers.size-1)
        {
            if (debug)          print(" " +(numbers.get(i)))

        }
        if (debug)      println()
        val x = moveDown(numbers)
        if (debug)     println(x)
        sum=sum + numbers.last()+x
        println(sum.toString() + " - " + numbers.last()+ " " + x.toString())
    })
    println(sum)
}

fun moveDown(numbers : List<Long>): Long{
    var numbers2 = mutableListOf<Long>()
    var max: Long =0
    for (i in 1..numbers.size-1)
    {
        numbers2.add(numbers.get(i)-numbers.get(i-1))
        print(" " + (numbers.get(i)-numbers.get(i-1)))
    }
    println()
    if (numbers2.min()!=0L || numbers2.max()!=0L) {
       max = moveDown(numbers2)
    }
   // println(max.toString() + " <---")
    return (numbers2.last()+max)
}
fun Game_07_01(inputLines: List<String>, debug : Boolean) {
    val strength  = listOf( 'A','K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3',  '2')
    println(strength)
    inputLines.forEach({it ->
        print("d")
        })
//    var locations = mutableListOf<Long>()
//    val hands = inputLines[].split("seeds: ")[1].run { split(" ").map { it.toLong() } }

}
    fun Game_05_02(inputLines: List<String>, debug : Boolean) {
    var sum = 0
    var locations = mutableListOf<Long>()
    val seeds = inputLines[0].split("seeds: ")[1].run { split(" ").map { it.toLong() } }
    for (k in 0..seeds.size-1 step(2))  // it takes forever :(
    {
        println(seeds[k].toString())
        println(seeds[k+1].toString())
        for (j in 0..seeds[k+1]-1){

            var i = mapperAlt ("seed-to-soil map:" ,"soil-to-fertilizer map:",inputLines, seeds[k]+j)
            if (debug) print((seeds[k]+j).toString() + " corresponds to - "+ i.toString())
            i = mapperAlt("soil-to-fertilizer map:" ,"fertilizer-to-water map:",inputLines, i )
            if (debug) print(" - "+ i.toString() + " - ")
            i = mapperAlt ("fertilizer-to-water map:" ,"water-to-light map:",inputLines,i )
            if (debug) print(i.toString() + " - ")
            i = mapperAlt ("water-to-light map:" ,"light-to-temperature map:",inputLines,i )
            if (debug) print(i.toString() + " - ")
            i = mapperAlt ("light-to-temperature map:" ,"temperature-to-humidity map:",inputLines ,i)
            if (debug) print(i.toString() + " - ")
            i = mapperAlt ("temperature-to-humidity map:","humidity-to-location map:",inputLines ,i)
            if (debug) print(i.toString() + " - ")
            i = mapperAltLast ("humidity-to-location map:",inputLines,i )
            locations.add(i)
            if (debug) println(i.toString())

        }
    }
//    var seedToSoil = mapper ("seed-to-soil map:" ,"soil-to-fertilizer map:",inputLines)
    println(min(locations))
}


fun Game_05_01(inputLines: List<String>, debug : Boolean) {
    var sum = 0
    val seeds = inputLines[0].split("seeds: ")[1].run { split(" ").map { it.toLong() } }

//    var seedToSoil = mapper ("seed-to-soil map:" ,"soil-to-fertilizer map:",inputLines)

    var locations = mutableListOf<Long>()
    seeds.forEach({
        var i = mapperAlt ("seed-to-soil map:" ,"soil-to-fertilizer map:",inputLines, it)
        print("$it corresponds to - "+ i.toString())
        i = mapperAlt("soil-to-fertilizer map:" ,"fertilizer-to-water map:",inputLines, i )
        print(" - "+ i.toString() + " - ")
        i = mapperAlt ("fertilizer-to-water map:" ,"water-to-light map:",inputLines,i )
        print(i.toString() + " - ")
        i = mapperAlt ("water-to-light map:" ,"light-to-temperature map:",inputLines,i )
print(i.toString() + " - ")
         i = mapperAlt ("light-to-temperature map:" ,"temperature-to-humidity map:",inputLines ,i)
        print(i.toString() + " - ")
         i = mapperAlt ("temperature-to-humidity map:","humidity-to-location map:",inputLines ,i)
        print(i.toString() + " - ")
         i = mapperAltLast ("humidity-to-location map:",inputLines,i )
        locations.add(i)
        println(i.toString())
    })

   println(min(locations))
}

fun min(locations: MutableList<Long>) :String {
    var i = Long.MAX_VALUE
    locations.forEach { it ->
        if (it<i) {i=it }
    }
    return i.toString()
}
fun mapperAlt (from : String, to : String,inputLines : List<String>,inp:Long) : Long {

        for (i in inputLines.indexOf(from)+1..inputLines.indexOf(to)-2){
        val range1 =(inputLines.get(i).split(" "))
        // println(inputLines.get(i).split(" "))
        if ((inp-range1[1].toLong())>=0 && (inp-range1[1].toLong()<=range1[2].toLong()))
         { return range1[0].toLong()+inp-range1[1].toLong() }
    //    for (j in 0..range1[2].toLong()-1) {
    //        if (inp == (range1[1].toLong() + j)) return (range1[0].toLong() + j)
    //    }

    }
    return inp
}

fun mapperAltLast(from : String,inputLines : List<String>,inp : Long) : Long {
    //var seedToSoil = mutableMapOf<Long,Long>()
    for (i  in inputLines.indexOf(from)+1..inputLines.size-2){
        val range1 =(inputLines.get(i).split(" "))
        if ((inp-range1[1].toLong())>0 && (inp-range1[1].toLong()<range1[2].toLong()))
        { return range1[0].toLong()+inp-range1[1].toLong() }

//        for (j in 0..range1[2].toLong()-1) {
//            if (inp==range1[1].toLong()+j) return range1[0].toLong()+j
 //       }
        //println(inputLines.get(i).split(" "))
    }
    return inp
}
fun Game_04_02(inputLines: List<String>, debug : Boolean) {
    var sum = 0
    var cards = mutableListOf<Int>()
// init
    inputLines.forEachIndexed { row, it ->
     cards.add(row, 1)
    }
    // collect numbers
    inputLines.forEachIndexed { row, it ->
        var numbers = mutableListOf<String>()
        for (i in it.indexOf(':')+2..it.indexOf('|')-1 step 3 ) {
            // println(" -"+ it.substring(i)+ "-")
            // println(" -"+ it.substring(i,i + 2)+ "-")
            numbers.add(it.substring(i,i+2).trim())
        }
        var found = 0
        for (i in it.indexOf('|')+2..it.length-1 step 3 ) {
            // println(" -"+ it.substring(i)+ "-")
            // println(" -"+ it.substring(i,i + 2)+ "-")
            if (numbers.contains(it.substring(i,i+2).trim())) found = found + 1

        }

//        println(found+1)
  //      sum = sum + 1
        if (found>0) {
           for (j in (1..found)){
               cards.set((row+j),cards.get(row+j)+1*cards.get(row))
           }
        }
        println(cards.get(row))
        sum = sum + cards.get(row)
      //  println(sum)

    }

    println(sum)
}
fun Game_04_01(inputLines: List<String>, debug : Boolean) {
    var sum = 0

    // collect numbers
    inputLines.forEachIndexed { i, it ->
        var numbers = mutableListOf<String>()
        for (i in it.indexOf(':')+2..it.indexOf('|')-1 step 3 ) {
           // println(" -"+ it.substring(i)+ "-")
           // println(" -"+ it.substring(i,i + 2)+ "-")
            numbers.add(it.substring(i,i+2).trim())

        }
        var found = -1
        for (i in it.indexOf('|')+2..it.length-1 step 3 ) {
            // println(" -"+ it.substring(i)+ "-")
            // println(" -"+ it.substring(i,i + 2)+ "-")
            if (numbers.contains(it.substring(i,i+2).trim())) found = found + 1

        }
        if (found>-1) sum = sum + Math.pow(2.0,found.toDouble()).toInt()
        println(sum)

    }
    println(sum)
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