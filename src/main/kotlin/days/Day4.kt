package days

class Day4 : Day(4) {

    override fun partOne(): Any {
        val drowing = inputList[0].split(",").map { it.toInt() }

        val cards = inputList.drop(1).fold(mutableListOf<Card>()) { cards, line ->
            if (line.trim().length == 0) {
                cards += Card()
            } else {
                val card = cards.last()
                card + line.trim().split("\\W+".toRegex()).map { it.toInt() }
            }
            cards
        }

        return drowing.asSequence().map {
            cards.forEach { card -> card - it }
            it to cards.find { it.isWin() }
        }.find {
            it.second != null
        }?.run {
            first * (second?.getSum() ?: 0)
        } ?: throw Exception()
    }

    override fun partTwo(): Any {
        val drowing = inputList[0].split(",").map { it.toInt() }

        val cards = inputList.drop(1).fold(mutableListOf<Card>()) { cards, line ->
            if (line.trim().length == 0) {
                cards += Card()
            } else {
                val card = cards.last()
                card + line.trim().split("\\W+".toRegex()).map { it.toInt() }
            }

            cards
        }

        return drowing.asSequence().map { drowed ->
            cards -= cards.fold(mutableListOf<Card>()) { wonCards, card: Card ->
                card - drowed
                if (card.isWin()) {
                    wonCards += card
                }
                wonCards
            }.toSet().also {
                if(cards.size == 1 && cards[0].isWin()) {
                    return@map drowed * cards[0].getSum()
                }
            }
        }.first {
            it is Int
        }
    }

    class Card() {
        private var count = 0
        private val rows = mutableMapOf<Int, Int>()
        private val cols = mutableMapOf<Int, Int>()

        operator fun plus(row: List<Int>) {
            count++
            row.forEachIndexed { i, v ->
                rows[v] = count
                cols[v] = i
            }
        }

        operator fun minus(value: Int) {
            if(rows[value] != null) {
                rows -= value
            }
            if(cols[value] != null) {
                cols -= value
            }
        }

        private fun hasEmptyRowOrCol(data: Map<Int, Int>): Boolean {
            return data.values.groupBy {
                it
            }.size < count
        }

        fun isWin(): Boolean {
            return hasEmptyRowOrCol(rows) || hasEmptyRowOrCol(cols)
        }

        fun getSum(): Int {
            return rows.keys.sum()
        }
    }
}
