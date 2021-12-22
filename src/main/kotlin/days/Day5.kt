package days


class Day5 : Day(5) {

    override fun partOne(): Any {
        return readLines(inputList).filter {
            it.isVertical() || it.isHorizontal()
        }.count()
    }

    override fun partTwo(): Any {
        return readLines(inputList).count()
    }
}

fun readLines(inputList: List<String>): List<Line> {
    return inputList.fold(mutableListOf()) { lines: List<Line>, str: String ->
        val (x1, y1, x2, y2) = str.split(Regex("->"), 2
        ).map {
            it.trim(
            ).split(","
            ).map {
                it.toInt()
            }
        }.flatten()

        lines + Line(x1, y1, x2, y2)
    }
}

fun List<Line>.count(): Int {
    return map {
        it.toPoints()
    }.flatten(
    ).groupBy {
        it
    }.filter {
        it.value.size > 1
    }.count()
}

data class Line(val x1: Int, val y1: Int, val x2: Int, val y2: Int) {
    fun isHorizontal(): Boolean {
        return x1 == x2
    }

    fun isVertical(): Boolean {
        return y1 == y2
    }

    fun makeRange(start: Int, end: Int): Iterable<Int> {
        return start.compareTo(end).let {
            when {
                it < 0 -> start..end
                it > 0 -> start downTo end
                else -> object : Iterable<Int> {
                    override fun iterator(): Iterator<Int> = object : Iterator<Int> {
                        override fun hasNext() = true
                        override fun next() = start
                    }
                }
            }
        }
    }

    fun toPoints(): List<Point> {
        return makeRange(x1, x2).zip(
            makeRange(y1, y2)
        ).fold(emptyList<Point>()) { list, coords ->
            list + Point(coords.first, coords.second)
        }
    }
}

data class Point(val x: Int, val y: Int)
