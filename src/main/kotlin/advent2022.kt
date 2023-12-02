import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

fun  main(args: Array<String>) {

val client = HttpClient.newBuilder().build()
val request = HttpRequest.newBuilder()
    .uri(URI.create("https://adventofcode.com/2022/day/3/input"))
    .header("Cookie","_ga=GA1.2.295804623.1701507354; _gid=GA1.2.131787216.1701507354; session=53616c7465645f5fda3577ccfc12109342e8ec2e219ce4ccdc420e56d526eb4f59b00e396a73b67de8c5c4686db7b47d1bd6343dc6c28fd6fd35bce3818065d4; _ga_MHSNPJKWC7=GS1.2.1701511847.2.0.1701511847.0.0.0")
    .build()
val response = client.send(request, HttpResponse.BodyHandlers.ofString())

val inputData = response.body()
val inputData3 = "vJrwpWtwJgWrhcsFMMfFFhFp\n" +
        "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL\n" +
        "PmmdzqPrVvPwwTWBwg\n" +
        "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn\n" +
        "ttgJtRGJQctTZtZT\n" +
        "CrZsJsPPZsGzwwsLwLmpwMDw"
  val inputLines =  inputData.split("\n")
  Game_03_2(inputLines, true)
}


fun Game_03_2(inputLines: List<String>, debug : Boolean) {
    var sum = 0

    for (i in 0..inputLines.size-2 step 3)
    {
        // commonChar(it.substring(0,it.length/2))
        if (debug) {
            println(inputLines.get(i))
            println(inputLines.get(i+1))
            println(inputLines.get(i+2))
            println(commonChar2(inputLines.get(i),inputLines.get(i+1),inputLines.get(i+2)))

            println(getValue2(inputLines.get(i),inputLines.get(i+1),inputLines.get(i+2)))

        }
        sum = sum + getValue2(inputLines.get(i),inputLines.get(i+1),inputLines.get(i+2))
    }

    println(sum)
}

fun getValue2(a : String, b : String, c: String) : Int
{
    var lista = 'a'..'z'
    var listA = 'A'..'Z'
    val common = commonChar2(a,b,c)
    lista.forEachIndexed { i, it2 ->
        if (it2==common) return i+1
    }
    listA.forEachIndexed { i, it2 ->
        if (it2==common) return i+27
    }
    return 0
}
fun commonChar2(a: String, b : String, c : String): Char{
    a.forEach { it ->
        if (b.contains(it,false) and c.contains(it,false))  {return it}

    }
    b.forEach { it ->
        if (a.contains(it,false) and c.contains(it,false)) {return it}

    }
    c.forEach { it ->
        if (b.contains(it,false) and a.contains(it,false)) {return it}

    }
    println("----")
    return ' '
}
fun Game_03(inputLines: List<String>, debug : Boolean) {
    var sum = 0

    inputLines.forEach { it ->
       // commonChar(it.substring(0,it.length/2))
        if (debug) {
            println(it)
            println(it.substring(0,it.length/2))
            println(it.substring(it.length/2))
            println(getValue(it))

        }
        sum = sum + getValue(it)
    }
    println(sum)
}
fun getValue(it : String): Int {
    var lista = 'a'..'z'
    var listA = 'A'..'Z'

    lista.forEachIndexed { i, it2 ->
        if (it2==commonChar(it.substring(0,it.length/2),it.substring(it.length/2))) return i+1
    }
    listA.forEachIndexed { i, it2 ->
        if (it2==commonChar(it.substring(0,it.length/2),it.substring(it.length/2))) return i+27
    }
    return 0
}
fun commonChar(first: String, second : String): Char{
    first.forEach { it ->
       if (second.contains(it,false)) {return it}

    }
    return ' '
}