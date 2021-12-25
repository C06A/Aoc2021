package days

import kotlin.math.roundToInt

class Day7 : Day(7) {

    override fun partOne(): Any {
        return inputList[0].split(","
        ).map {
            it.toInt()
        }.sorted().let { seq ->
            val half = seq.drop(seq.size / 2).reversed()
            seq.zip(half).fold(0) { sum, x: Pair<Int, Int> ->
                sum + if (x.first > x.second) {
                    x.first - x.second
                } else {
                    x.second - x.first
                }
            }
        }
    }

    override fun partTwo(): Any {
        return inputList[0].split(
            ","
        ).map { it.toInt()
        }.sorted().let { seq ->
            seq.average(
            ).toInt(
            ).let { listOf(it, it+1) // Somehow sometimes it should be round up or down, but I didn't figure out why
            }.map { avg ->
                seq.map { it.toInt() }.fold(0) { fuel, coord ->
                    fuel + (if (coord > avg) {
                        coord - avg
                    } else {
                        avg - coord
                    }).let {
                        (it * (it + 1)) / 2
                    }
                }
            }.minOf { it }
        }
    }
}
