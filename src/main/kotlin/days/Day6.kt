package days

import java.math.BigInteger

class Day6 : Day(6) {

    override fun partOne(): Any = afterGeneration(80)

    override fun partTwo(): Any = afterGeneration(256)

    private fun afterGeneration(gen: Int): BigInteger {
        return (1..gen).fold(inputList[0].mapToAges()) { list, _ ->
            list[0].let { first ->
                (list.drop(1).fold<Long, List<Long>>(listOf()) { list, age ->
                    list + age
                } + first).toLongArray().also { array ->
                    array[6] += first
                }.toList()
            }
        }.sumOf {
            println(it)
            it.toBigInteger()
        }
    }
}

fun String.mapToAges(): List<Long> {
    return split(","
    ).fold(mutableListOf(0,0,0,0,0,0,0,0,0)) { list, age ->
        list.also {
            it[age.toInt()]++
        }
    }
}
