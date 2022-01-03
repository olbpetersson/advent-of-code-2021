package advent_of_code_2021

import java.io.File

@ExperimentalStdlibApi
class DayFour {
    private val file = File(DayTwo::class.java.getResource("/input_day4.txt").file)

    fun taskOne(): Int {
        val lines = file.readLines()
        val draws = lines[0].split(",").map { it.toInt() }
        val boardLines = lines - lines[0]
        val boards = boardLines.chunked(6).map {
            Board(it)
        }
        (5 until draws.size).forEach { index ->
            boards.forEach { board ->
                val currentDraws = draws.subList(0, index)
                val hasBingo = board.hasBingo(currentDraws)
                if (hasBingo) {
                    val unmarked = board.getUnmarked(currentDraws)
                    return unmarked.sum() * draws[index - 1]
                }
            }
        }
        return 0
    }

    fun taskTwo(): Int {
        val lines = file.readLines()
        val draws = lines[0].split(",").map { it.toInt() }
        val boardLines = lines - lines[0]
        val boards = boardLines.chunked(6).map {
            Board(it)
        }
        val (index, board) = findLastBoard(boards, draws, 6)
        val unmarked = board.getUnmarked(draws.subList(0, index+1))
        return unmarked.sum() * draws[index]
    }

    fun findLastBoard(boards: List<Board>, draws: List<Int>, drawIndex: Int): Pair<Int, Board> {
        val currentDraws = draws.subList(0, drawIndex)
        if (boards.size == 1 && boards[0].hasBingo(currentDraws)) {
            return drawIndex-1 to boards[0]
        }

        val newBoards = boards.filterNot { board -> board.hasBingo(currentDraws) }
        return findLastBoard(newBoards, draws, drawIndex + 1)
    }

    class Board(rawInput: List<String>) {
        private val rows = (1..5).map { index ->
            rawInput[index].split("\\s+".toRegex())
                .filter { char -> char.isNotBlank() }
                .map { numeric ->
                    numeric.toInt()
                }
        }
        private val columns = (0..4).map { index ->
            rows.map { row -> row[index] }
        }

        fun hasBingo(draws: List<Int>): Boolean {
            val candidates = rows + columns
            val bingoRow = candidates.find { candidate ->
                candidate.all { element ->
                    draws.contains(element)
                }
            }
            return bingoRow != null
        }

        fun getUnmarked(draws: List<Int>): Set<Int> {
            val candidates = rows + columns
            return candidates.flatten()
                .filter { candidate -> !draws.contains(candidate) }
                .toSet()
        }
    }

}

@ExperimentalStdlibApi
fun main() {
    val dayFour = DayFour()
    val result = dayFour.taskTwo()
    println("result is $result")
}
