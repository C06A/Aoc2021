package days

class Day9 : Day(9) {

    override fun partOne(): Any {
        return inputList.let {
            val addition = MutableList(it[0].length + 2) { 10 }
            val input = it.joinToString().toByteArray().map {
                if (it >= '0'.code) {
                    it - '0'.code
                } else {
                    10
                }
            }
            gradient(listOf(10) + input + addition, 1)
                .zip(
                    gradient(addition + input + addition, addition.size)
                ).mapIndexed { i, (h, v) ->
                    if (h && v) {
                        input[i]
                    } else {
                        null
                    }
                }.filterNotNull().sumOf { it + 1 }
        }
    }

    override fun partTwo(): Any {
        return inputString.split("\n")
            .filterNot { it.isEmpty() }
            .map { it.uppercase() }
            .last()
    }

    fun gradient(str: List<Int>, offset: Int): BooleanArray {
        return str.zip(str.drop(offset)).map {
            it.first.compareTo(it.second)
        }.let {
            it.zip(it.drop(offset)).map {
                it.first > 0 && it.second < 0
            }
        }.toBooleanArray()
    }
}
