package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsInstanceOf.instanceOf
import org.hamcrest.core.IsNull.notNullValue
import org.junit.jupiter.api.Test

class Day9Test {

    private val dayNine = Day9()

    @Test
    fun testPartOne() {
        assertThat(dayNine.partOne(), `is`(15))
    }

    @Test
    fun testPartTwo() {
        val partTwo = dayNine.partTwo()
        assertThat(partTwo, notNullValue())
        assertThat(partTwo, instanceOf(String::class.java))
        assertThat(partTwo, `is`("FILE"))
    }
}
