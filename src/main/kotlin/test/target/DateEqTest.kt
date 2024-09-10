package test.target

import test.Test
import test.TestClass
import test.assertTrue
import java.time.ZonedDateTime

class DateEqTest: TestClass() {

    @Test
    fun `date eq`() {
        val date = "2007-12-03T10:15:30+01:00[Europe/Paris]"
        val date2 = "2007-12-03T10:15:30+01:00[Europe/Paris]"
        assertTrue(ZonedDateTime.parse(date) == ZonedDateTime.parse(date2))
    }

}
