package advent_of_code_2021

import java.io.File

@ExperimentalStdlibApi
class DayThree {
    private val file = File(DayTwo::class.java.getResource("/input_day_3.txt").file)

    @OptIn(ExperimentalStdlibApi::class)
    fun taskOne(): Int {
        val emeliesNotePad = mutableListOf(0, 0, 0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0)

        val lines = file.readLines()
        lines.forEach { line ->
            line.forEachIndexed { index, value ->
                emeliesNotePad[index] += value.digitToInt()
            }
        }

        val binaryString = emeliesNotePad.map { value ->
            if(value > lines.size/2) {
                "1"
            } else {
                "0"
            }
        }.joinToString("")
        val epsilonString = binaryString.map {
            it.digitToInt() xor 1
        }.joinToString("")
        val gamma = binaryString.toInt(2)
        val epsilon = epsilonString.toInt(2)
        println("gamma is $gamma")
        println("epsilon is $epsilon")
        return gamma * epsilon
    }

    @OptIn(ExperimentalStdlibApi::class)
    fun taskTwo(): Int {
        val lines = file.readLines()
        return doItRecursive(0, lines).toInt(2)
    }

    private fun doItRecursive(index: Int, survivors: List<String>): String {
        return if(survivors.size ==  1) {
            survivors[0]
        } else {
            val mostCommonMap = mutableMapOf(
                0 to mutableListOf<String>(),
                1 to mutableListOf<String>()
            )
            survivors.forEach { line ->
                val firstBit = line[index].digitToInt()
                mostCommonMap[firstBit]!!.add(line)
            }
            val newSurvivors = if (mostCommonMap[0]!!.size > mostCommonMap[1]!!.size) {
                mostCommonMap[0]
            } else {
                mostCommonMap[1]
            }
            doItRecursive(index + 1, newSurvivors!!)
        }
    }
}

@ExperimentalStdlibApi
fun main() {
    val dayThree = DayThree()
    val result = dayThree.taskTwo()
    println("result is $result")
}
