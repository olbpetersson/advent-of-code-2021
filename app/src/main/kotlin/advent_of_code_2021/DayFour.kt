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
                val bingoRow = board.getUnmarkedIfBingo(draws.subList(0, index))
                bingoRow?.let {
                    return bingoRow.sum() * draws[index-1]
                }
            }
        }
        return 0
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

        fun getUnmarkedIfBingo(draws: List<Int>): Set<Int>? {
            val candidates = rows + columns
            val bingoRow = candidates.find { candidate ->
                candidate.all { element ->
                    draws.contains(element)
                }
            }

            return bingoRow?.let {
                val nonMarked = candidates.flatten()
                    .filter { candidate -> !draws.contains(candidate) }
                nonMarked.toSet()
            }
        }
    }

}

@ExperimentalStdlibApi
fun main() {
    val dayFour = DayFour()
    val result = dayFour.taskOne()
    println("result is $result")
}
