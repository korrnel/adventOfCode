fun main(args: Array<String>) {

    val inputData = Common.getData(16)


    var inputData1 =
                        ".|...\\....\n" +
                        "|.-.\\.....\n" +
                        ".....|-...\n" +
                        "........|.\n" +
                        "..........\n" +
                        ".........\\\n" +
                        "..../.\\\\..\n" +
                        ".-.-/..|..\n" +
                        ".|....-|.\\\n" +
                        "..//.|...."
        println( Day16.Game_01(inputData.trim().split("\n"),false))
}

class Day16 {
    companion object {

        var energized = mutableListOf<MutableList<Int>>()
        fun Game_01(inputLines: List<String>, debug: Boolean) : Int {
            energized = MutableList(inputLines.size) { MutableList(inputLines[0].length) { 0 } }
            println(inputLines)
            var row = 0
            var col = 0
            var direction = 2
            Beam(inputLines,row,col,direction)

            return energized.sumOf { it.count(    { it > 0}
            ) }

        }

        private fun Beam(inputLines : List<String>, rowIn: Int, colIn: Int, directionIn: Int): Int {
            var row = rowIn
            var col = colIn
            var direction = directionIn
            var i=0
            while (row < inputLines.size && col < inputLines[0].length && row > -1 && col > -1) {
                if (energized[row][col] == direction) return -1 // if we were here already
                energized[row][col] = direction

                // changes
                if (inputLines[row][col]=='|') {
                    if ((direction==2 )||(direction==4)) {
                        Beam(inputLines,row-1,col,1)
                        direction = 3
                    }
                }
                if (inputLines[row][col]=='-') {
                    if ((direction==1 )||(direction==3)) {
                        Beam(inputLines,row,col+1,2)
                        direction = 4
                    }
                }
                if (inputLines[row][col]=='/') {
                    if ((direction==1)) {
                        direction = 2
                    } else if ((direction==2)) {
                        direction = 1
                    } else if ((direction==3)) {
                        direction = 4
                    } else if ((direction==4)) {
                        direction = 3
                    }
                }
                if (inputLines[row][col]=='\\') {
                    if ((direction==1)) {
                        direction = 4
                    } else if ((direction==2)) {
                        direction = 3
                    } else if ((direction==3)) {
                        direction = 2
                    } else if ((direction==4)) {
                        direction = 1
                    }

                }
                // move
                 if (direction==1) row = row-1
                 if (direction==2) col = col+1
                 if (direction==3) row = row+1
                 if (direction==4) col = col-1

            }
            return  -1
        }


    }
}