package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsInstanceOf.instanceOf
import org.hamcrest.core.IsNull.notNullValue
import org.junit.jupiter.api.Test

class Day8Test {

    private val dayEight = Day8()

    @Test
    fun testPartOne() {
        assertThat(dayEight.partOne(), `is`(26))
    }

    @Test
    fun testPartTwo() {
        assertThat(dayEight.partTwo(), `is`(61229))
    }
}
