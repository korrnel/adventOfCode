package aoc2022

fun  main(args: Array<String>) {

val inputData = Common.getData(3, if (args.size>0) args.get(0) else "dev","2022")
    val inputData3 = "vJrwpWtwJgWrhcsFMMfFFhFp\n" +
        "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL\n" +
        "PmmdzqPrVvPwwTWBwg\n" +
        "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn\n" +
        "ttgJtRGJQctTZtZT\n" +
        "CrZsJsPPZsGzwwsLwLmpwMDw"
  val inputLines =  inputData.trim().split("\n")
  Game01(inputData3.split("\n"), false)
  Game02(inputData3.split("\n"), false)
  Game01(inputLines, false)
  Game02(inputLines, false)
}


fun Game02(inputLines: List<String>, debug : Boolean) {
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
fun Game01(inputLines: List<String>, debug : Boolean) {
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
    // find the commons in each part

    // if it is a lower char
    lista.forEachIndexed { i, it2 ->
        if (it2== commonChar(it.substring(0,it.length/2),it.substring(it.length/2))) return i+1
    }
    // if it is a capital
    listA.forEachIndexed { i, it2 ->
        if (it2== commonChar(it.substring(0,it.length/2),it.substring(it.length/2))) return i+27
    }
    // there's no match
    return 0
}
fun commonChar(first: String, second : String): Char{

    first.forEach { it ->
       if (second.contains(it,false)) {return it}
    }
    return ' '
}