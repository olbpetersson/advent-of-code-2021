package advent_of_code_2021

import java.io.File

class DayTwo {
    private val file = File(DayTwo::class.java.getResource("/input_day2.txt").file)

    fun taskOne(): Int {
        var forwardCounter = 0
        var upCounter = 0
        var downCounter = 0
        file.forEachLine { line ->
            val commandAndValue = line.split(" ")
            val command = commandAndValue[0]
            val value = commandAndValue[1].toInt()
            when(command) {
                "forward" -> forwardCounter += value
                "up" -> upCounter += value
                "down" -> downCounter += value
                else -> println("We have error in our input.")
            }
        }
        return forwardCounter * (downCounter - upCounter)
    }

    fun taskTwo(): Int {
        var horizontal = 0
        var depth = 0
        var aim = 0
        file.forEachLine { line ->
            val commandAndValue = line.split(" ")
            val command = commandAndValue[0]
            val value = commandAndValue[1].toInt()
            when(command) {
                "forward" -> {
                    horizontal += value
                    depth += aim * value
                }
                "up" -> aim -= value
                "down" -> aim += value
                else -> println("We have error in our input.")
            }
        }
        return horizontal * depth
    }

}

fun main() {
    val dayTwo = DayTwo()
    val answer = dayTwo.taskTwo()
    println("The answer is $answer")
}
