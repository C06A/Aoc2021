package days

class Day8 : Day(8) {

    override fun partOne(): Any {
        return inputList.map {
            it.split("|")[1]
        }.map {
            it.split(Regex("\\W"))
        }.flatten(
        ).filter {
            it.trim().length in setOf(2, 3, 4, 7)
        }.count()
    }

    override fun partTwo(): Any {
        return inputList.map {
            it.split(
                "|"
            ).map {
                it.split(
                    Regex("\\W")
                ).map {
                    it.toByteArray().toSet()
                }
            }.let {
                it[0] to it[1]
            }
        }.map { (sample, results) ->
            (sample + results) to results
        }.map { (whole, results) ->
            /*
            1 -- * * с * * f * -- 2 = 1
            4 -- * b c d * f * -- 4 = 4
            7 -- a * c * * f * -- 3 = 7
            8 -- a b c d e f g -- 7 = 8
            9 -- a b c d * f g -- 6 & includes 4
            0 -- a b c * e f g -- 6 & includes 1
            6 -- a b * d e f g -- 6 & not includes 1
            3 -- a * c d * f g -- 5 & includes 1
            2 -- a * c d e * g -- 5 & overlaps with 4 in 2 places
            5 -- a b * d * f g -- 5 & overlaps with 4 in 3 places
             */
            whole.distinct().filterNot { it.isNullOrEmpty() }.groupBy {
                it.size
            }.let { mapBySize ->
                mapBySize.entries.fold(
                    mapOf(
                        mapBySize[2]!![0] to 1,
                        mapBySize[4]!![0] to 4,
                        mapBySize[3]!![0] to 7,
                        mapBySize[7]!![0] to 8
                    )
                ) { mapBySample, (len, samples) ->
                    if (samples.size > 1) {
                        samples.fold(mapBySample) { map, sample ->
                            map + Pair(sample, if (sample.containsAll(mapBySize[4]!![0])) {
                                9
                            } else if (sample.containsAll(mapBySize[2]!![0])) {
                                if (len == 6) {
                                    0
                                } else {
                                    3
                                }
                            } else {
                                if (len == 6) {
                                    6
                                } else if ((sample - mapBySize[4]!![0]).size == 2) {
                                    5
                                } else {
                                    2
                                }
                            })
                        }
                    } else {
                        mapBySample
                    }
                }.let {
                    results.filterNot {
                        it.isNullOrEmpty()
                    }.fold(0) { sum, indicator ->
                        sum * 10 + it[indicator]!!
                    }
                }
            }
        }.sum()
    }
}
