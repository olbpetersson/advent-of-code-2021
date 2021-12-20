package advent_of_code_2021

import java.io.File

class DayOne {
    private val file = File(DayOne::class.java.getResource("/input_day1_task1.txt").file)
    fun taskOne(): Int {
        val lines = file.readLines().map { it.toInt() }
        var counter: Int = 0
        var previous: Int = lines[0]
        var next: Int = -1
        (1 until lines.size).forEach { index ->
            next = lines[index]
            if(next > previous) {
                counter++
            }
            previous = next
        }
        return counter
    }

    fun taskTwo(): Int {
        val lines = file.readLines().map { it.toInt() }
        var counter: Int = 0

        val tripleList = lines.mapIndexed { index, value ->
            if(index+2 >= lines.size) {
                null
            } else {
                Triple(lines[index], lines[index+1], lines[index+2])
            }
        }.filterNotNull()

        var previous = tripleList[0]
        var next: Triple<Int, Int, Int> = Triple(-1, -1, -1)
        (1 until tripleList.size).forEach { index ->
            next = tripleList[index]
            if(next.sum() > previous.sum()) {
                counter++
            }
            previous = next
        }
        return counter
    }

    fun Triple<Int, Int, Int>.sum(): Int {
        return this.first + this.second + this.third
    }

}

fun main() {
    val dayOne = DayOne()
    val numberOfCounts = dayOne.taskTwo()
    print("The answer is $numberOfCounts")

}
