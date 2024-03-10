package aoc2022

import com.sun.jna.platform.win32.Guid
import com.sun.jna.platform.win32.Guid.GUID
import java.util.UUID


fun  main(args: Array<String>) {
// rock paper scissor
    val inputData = Common.getData(7, if (args.size > 0) args.get(0) else "dev", "2022")
    val inputData1 = "\$ cd /\n" +
            "\$ ls\n" +
            "dir a\n" +
            "14848514 b.txt\n" +
            "8504156 c.dat\n" +
            "dir d\n" +
            "\$ cd a\n" +
            "\$ ls\n" +
            "dir e\n" +
            "29116 f\n" +
            "2557 g\n" +
            "62596 h.lst\n" +
            "\$ cd e\n" +
            "\$ ls\n" +
            "584 i\n" +
            "\$ cd ..\n" +
            "\$ cd ..\n" +
            "\$ cd d\n" +
            "\$ ls\n" +
            "4060174 j\n" +
            "8033020 d.log\n" +
            "5626152 d.ext\n" +
            "7214296 k"

// part 1  - 4 distinct char
    println( Day07.Game_01(inputData1.trim().split("\n"), false,4))
    println( Day07.Game_01(inputData.trim().split("\n"), false,4))

//
}
class Day07 {
    companion object {
        const val SIZE_PARAM = 1_000_000
        data class Node(val NodeId : UUID, val parent: UUID?,val parentName : String?, val name: String, var size: Long, val type : NodeType, val depth : Int) {
            enum class NodeType {
                DIRECTORY,
                FILE
            }
        }

        tailrec fun sumDir( NodeId:UUID) : Long {
            var sum = 0L
            nodes.filter { it.parent==NodeId }.forEach {
                if (it.type==Node.NodeType.DIRECTORY)
                {
                    sum+= sumDir(it.NodeId)
                } else
                {
                    sum += it.size
                }
            }
            return  sum
        }
        fun sumTheDirs(): MutableList<Node> {
            var innerList= nodes.toMutableList()
            val depth = innerList.maxOf { it.depth }
            for (i in (1 until depth).reversed())
            {
                innerList.filter { it.depth==i }.filter { it.type== Node.NodeType.DIRECTORY }.forEach {
                    it.size = innerList
                        .filter { it2 -> it2.parent==it.NodeId }
                        .sumOf { it2-> it2.size }
                }
            }
            return innerList
        }

        fun sumTheDirs_recursive(depth: Int): MutableList<Node> {
            var innerList= nodes.toMutableList()
            innerList.forEach {
                    if (it.type==Node.NodeType.DIRECTORY)
                    {
                        it.size = sumDir(it.NodeId)
                    }
             }
            return innerList
        }


        fun sumTheFiles() : Long {
            var sum = 0L
            nodes.forEach { if (it.type==Node.NodeType.FILE) sum+= it.size }
            return sum
        }
        var nodes = mutableListOf<Node>()
        fun Game_01(inputLines: List<String>, debug: Boolean,slice: Int): Long {
            var parent : Node = Node(UUID.randomUUID(), null,null,"/",0,Node.NodeType.DIRECTORY,0)
            nodes = mutableListOf<Node>()
           // walk the tree
            var depth = 0
            inputLines.forEach { it
                when {
                    it.startsWith("$ cd ..") ->
                        parent= nodes.filter { it.NodeId==parent.parent }.getOrNull(0)!!
                        // let's moveUp

                    (it.startsWith("$ cd ")&&(!(it.startsWith("$ cd ..")))) ->
                       // set goDeeper
                      {
                          parent = Node(UUID.randomUUID(), parent?.NodeId,parent?.name,it.substring(5),0,Node.NodeType.DIRECTORY,
                              parent.depth+1
                          )
                          nodes.add(parent)

                      }
                    it.startsWith("dir ") ->
                       // list Dirs
                        null
               //        nodes.add(Node(UUID.randomUUID(),parent?.NodeId,parent?.name, it.split(" ").get(1),0,Node.NodeType.DIRECTORY,parent.depth))
                    !it.startsWith("$") ->
                       // file so count
                       nodes.add(Node(UUID.randomUUID(),parent?.NodeId,parent?.name, it.split(" ").get(1),it.split(" ").get(0).toLong(),Node.NodeType.FILE,parent.depth+1))

                }
            }

           // println(nodes)
            nodes = sumTheDirs()
            println(nodes)
            return nodes.filter { it.type== Node.NodeType.DIRECTORY }.filter { it.size<=100000 }.sumOf { it.size }

        }




    }
}