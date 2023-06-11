fun main() {

    // 
    /*
        # -> Walls, Duvarlar,
        . -> Positions robot can move, robotun hareket edebileceği noktalar
        * -> initial position of robot, robotun başlangıç pozistonu
    */

    val room1 = arrayOf(
        "#######",
        "#.....#",
        "#..*..#",
        "#.....#",
        "#.....#",
        "#######"
    )

    val room2 = arrayOf(
        "#######",
        "#.....#",
        "#..*..#",
        "#.....#",
        "#.....#",
        "#######"
    )

    val room3 = arrayOf("########", "#.*.#..#", "#......#", "#...#..#", "########")
    val room4 = arrayOf("########", "#.##...#", "#.#..#.#", "#*..##.#", "########")
    val room5= arrayOf("########", "###....#", "###..#.#", "#....*.#", "#...##.#", "########")

    val path = clean(room5)
    println(path)
}

fun clean(room: Array<String>): String {
                          // Up(Yukarı), Right (Sağ), Down(Aşağı), Left(Sol)     
    val directions = arrayOf(Pair(-1, 0),Pair(0, 1), Pair(1, 0), Pair(0, -1), )
    val numRows = room.size
    val numCols = room[0].length
    val visited = Array(numRows) { BooleanArray(numCols) }
    var currentRow = -1
    var currentCol = -1
    val result = StringBuilder();

    // Find the initial position of the robot
    for (row in room.indices) {
        for (col in room[row].indices) {
            // set visited all walls in map
            if(room[row][col] == '#')
                visited[row][col] = true
            if (room[row][col] == '*') {
                currentRow = row
                currentCol = col
            }
        }
    }

    fun dfs(row: Int, col: Int) {
        visited[row][col] = true
        for (direction in directions){
            val newRow = row + direction.first
            val newCol = col + direction.second

            if(newRow in visited.indices && newCol in 0 until visited[0].size && !visited[newRow][newCol]){
                visited[newRow][newCol] = true
                when(direction){
                    Pair(-1, 0) -> {
                        result.append("^")
                    }
                    Pair(0,1) ->{
                        result.append(">")
                    }
                    Pair(1,0) -> {
                        result.append("v")
                    }
                    Pair(0,-1) -> {
                        result.append("<")
                    }
                }
                dfs(newRow,newCol)
            }
        }
    }



    dfs(currentRow, currentCol)


    return result.toString()
}