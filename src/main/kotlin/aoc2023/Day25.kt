package aoc2023

import java.util.*

fun main(args: Array<String>) {

    val inputData = Common.getData(25,"dev", "2023")


    var inputData1 =
                                    "jqt: rhn xhk nvd\n" +
                                    "rsh: frs pzl lsr\n" +
                                    "xhk: hfx\n" +
                                    "cmg: qnr nvd lhk bvb\n" +
                                    "rhn: xhk bvb hfx\n" +
                                    "bvb: xhk hfx\n" +
                                    "pzl: lsr hfx nvd\n" +
                                    "qnr: nvd\n" +
                                    "ntq: jqt hfx bvb xhk\n" +
                                    "nvd: lhk\n" +
                                    "lsr: lhk\n" +
                                    "rzs: qnr cmg lsr rsh\n" +
                                    "frs: qnr lhk lsr"

    println( Day25.Game_01_alt(inputData1,false))
    println( Day25.Game_01_alt(inputData,false))
    println( Day25.Game_01(inputData1,false))

}

class Day25 {
    companion object {

        data class Path(val x: Int, val y: Int, val cost: Int, val direction: Int, val past : List<Pair<Int, Int>>)

        var nodes = mutableListOf<String>()
        var edges = mutableListOf<Pair<String,String>>()
        var subGraphs = mutableListOf<String>()

        fun Game_01_alt(inputLines: String,  debug: Boolean) : Int {
            val results = mutableListOf<Int>()
            //get the map
            nodes.clear()
            edges.clear()
            subGraphs.clear()
            inputLines.trim().split('\n').forEach {
                val from = it.split(':')[0].trim()
                val to = it.split(':')[1].trim().split(' ')
                nodes.add(from.trim())


                to.forEach { dest ->
                    // add the edges to the processing queue
                    edges.add(Pair(from.trim(), dest.trim())) // for later
                    // add the target node to the node list
                    nodes.add(dest.trim())
                }
            }


            nodes = nodes.distinct().toMutableList()

            val allEdges = edges.toMutableList()
 //           println (edges.distinct())

            // now walk the current map from each node
            println( allEdges.size)
            allEdges.remove(Pair("znv","ddj"))
            allEdges.remove(Pair("pzq","rrz"))
            allEdges.remove(Pair("mtq","jtr"))
            println( allEdges.size)
             var maxReachedNodes = theWalk(allEdges)

            // remove the most used nodes

          //  maxReachedNodes = theWalk(allEdges)


            if (debug) println(maxReachedNodes.distinct().sorted())
            subGraphs.add(maxReachedNodes.distinct().sorted().toString())
            println(subGraphs.distinct())
            if (maxReachedNodes.distinct().size>1) {
                return maxReachedNodes.distinct().get(0) * maxReachedNodes.distinct().get(1)
            }
            return -1
        }

        fun theWalk(allEdges: MutableList<Pair<String, String>>): MutableList<Int> {
            var map = mutableMapOf<String, Set<String>>()
            // build the map
            allEdges.forEach {
                map.put(
                    it.first,
                    mutableSetOf<String>(it.second).union(map.get(it.first) ?: mutableSetOf<String>())
                )
                map.put(
                    it.second,
                    mutableSetOf<String>(it.first).union(map.get(it.second) ?: mutableSetOf<String>())
                )
            }
            var edgesVisitedCount = mutableMapOf<Pair<String,String>,Int>()
            var maxReachedNodes = mutableListOf<Int>()
            // now walk the current map from each node
            nodes.forEach { node ->
                // init
                var queue = PriorityQueue<String>()
                var nodesVisited = mutableListOf<String>()
                queue.clear()
                queue.add(node)
                nodesVisited.clear()
                nodesVisited.add(node)
                // visit every node
                while (queue.isNotEmpty()) {
                    val sourceNode = queue.poll()
                    val targets = map.get(sourceNode)
                    targets?.forEach { targetNode ->
                        if (!(nodesVisited.contains(targetNode))) {
                            nodesVisited.add(targetNode)
                            // add new targets
                            if (!queue.contains(targetNode)) {
                                queue.add(targetNode)

                                // mark
                                var currentScore = edgesVisitedCount[Pair(sourceNode, targetNode)]?:edgesVisitedCount[Pair(targetNode,sourceNode)]?:0
                                edgesVisitedCount.remove(Pair(sourceNode, targetNode))
                                edgesVisitedCount.remove(Pair(targetNode, sourceNode))
                                currentScore += 1
                                edgesVisitedCount.put(Pair(sourceNode, targetNode),currentScore)
                            }
                        }
                    }

                }

                if (!(maxReachedNodes.contains(nodesVisited.size))){
                    maxReachedNodes.add(nodesVisited.size)
                }
            }
            println(edgesVisitedCount.values.sortedDescending().get(4))
            edgesVisitedCount.forEach {
                if (it.value==1453) println(it)
            }
            return maxReachedNodes
        }
        fun Game_01(inputLines: String,  debug: Boolean) : Int {
            val results = mutableListOf<Int>()

            nodes.clear()
            edges.clear()
            subGraphs.clear()
            //get the map
            inputLines.trim().split('\n').forEach {
                val from = it.split(':')[0].trim()
                val to = it.split(':')[1].trim().split(' ')
                nodes.add(from.trim())


                to.forEach { dest ->
                    // add the edges to the processing queue
                    edges.add(Pair(from.trim(), dest.trim())) // for later
                    // add the target node to the node list
                    nodes.add(dest.trim())
                }
            }
            // clean it
            nodes = nodes.distinct().toMutableList()

            for (i in 0 until edges.size - 2)
                for (j in i + 1 until edges.size - 1)
                    for (k in j + 1 until edges.size ) {
                        var map = mutableMapOf<String, Set<String>>()
                        val allEdges = edges.toMutableList()
                        var maxReachedNodes = mutableListOf<Int>()
                        if (debug) println(i.toString() + " " + j.toString() + " "+ k.toString())
                        allEdges.removeAt(i)
                        allEdges.removeAt(j-1)
                        allEdges.removeAt(k-2)
                        // build the map with the removed edges
                        allEdges.forEach {
                            map.put(
                                it.first,
                                mutableSetOf<String>(it.second).union(map.get(it.first) ?: mutableSetOf<String>())
                            )
                            map.put(
                                it.second,
                                mutableSetOf<String>(it.first).union(map.get(it.second) ?: mutableSetOf<String>())
                            )
                        }
                        // now walk the current map from each node
                        nodes.forEach { node ->
                            // init
                            var queue = PriorityQueue<String>()
                            var nodesVisited = mutableListOf<String>()
                            queue.clear()
                            queue.add(node)
                            nodesVisited.clear()
                            nodesVisited.add(node)
                            // visit every node
                            while (queue.isNotEmpty()) {
                                val sourceNode = queue.poll()
                                val targets = map.get(sourceNode)

                                targets?.forEach { targetNode ->
                                    if (!(nodesVisited.contains(targetNode))) {
                                        nodesVisited.add(targetNode)
                                        // add new targets
                                        if (!queue.contains(targetNode)) queue.add(targetNode)
                                        // mark
//                                        var currentScore = edgesVisitedCount[Pair(sourceNode, targetNode)]?:edgesVisitedCount[Pair(targetNode,sourceNode)]?:0
//                                        edgesVisitedCount.remove(Pair(sourceNode, targetNode))
//                                        edgesVisitedCount.remove(Pair(targetNode, sourceNode))
//                                        currentScore += 1
//                                        edgesVisitedCount.put(Pair(sourceNode, targetNode),currentScore)

                                    }
                                }

                            }
                            if (!(maxReachedNodes.contains(nodesVisited.size))){
                                maxReachedNodes.add(nodesVisited.size)
                            }
                        }
                        if (debug) println(maxReachedNodes.distinct().sorted())
                        subGraphs.add(maxReachedNodes.distinct().sorted().toString())
                        if (maxReachedNodes.distinct().size>1) {
                            return maxReachedNodes.distinct().get(0) * maxReachedNodes.distinct().get(1)
                        }
                    }
            println(subGraphs.distinct())
            return -1
           }


    }
}