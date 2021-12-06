package days

class Day3: Day(3) {

    override fun partOne(): Any {
        val onesCounts = Array<Int>(inputList[0].length) { 0 }

        val half = inputList.size / 2

        inputList.forEach {
            it.toCharArray().forEachIndexed { i, char ->
                if (char == '1') onesCounts[i]++
            }
        }
        val (most, least) = onesCounts.fold(0 to 0) { pair, count ->
            if( count > half) {
                (pair.first * 2 + 1) to (pair.second * 2)
            } else {
                (pair.first * 2) to (pair.second * 2 + 1)
            }
        }
        return most * least
    }

    override fun partTwo(): Any {
        val (generator, scrubber) = inputList.map { it.toCharArray() }.run {
            reduce {
                val ones: List<CharArray> = it['1'] ?: emptyList()
                val zeros: List<CharArray> = it['0'] ?: emptyList()
                if (zeros.size > ones.size) {
                    zeros
                } else {
                    ones
                }
            }[0] to reduce {
                val ones: List<CharArray> = it['1'] ?: emptyList()
                val zeros: List<CharArray> = it['0'] ?: emptyList()
                if (zeros.size > ones.size) {
                    ones
                } else {
                    zeros
                }
            }[0]
        }
        return String(generator).toInt(2) * String(scrubber).toInt(2)
    }

    fun List<CharArray>.reduce(
        bitIndx: Int = 0,
        reducer: (Map<Char, List<CharArray>>) -> List<CharArray>
    ): List<CharArray> {
        if (size == 1) {
            return this
        }

        return reducer(groupBy {
            it[bitIndx]
        }).reduce(bitIndx + 1, reducer)
    }
}
