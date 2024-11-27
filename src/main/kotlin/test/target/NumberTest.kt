package test.target

import test.Test
import test.TestClass
import test.assertEq
import java.sql.Timestamp
import java.util.Calendar

class NumberTest : TestClass() {

    @Test
    fun `check long equivalence with Timestamp`() {

        val value: Long = 1719658722

        val timestamp = Timestamp(1719658722)

        assertEq(value, timestamp.time)
    }

    @Test
    fun `date with format and number`() {
        fun addO(n: Int): String {
            return if (n < 10) "0$n" else "$n"
        }

        val date: Calendar = Calendar.getInstance()
        val formattedDate = String.format(
            "%s-%s-%s_%s-%s-%s",
            addO(date.get(Calendar.DAY_OF_MONTH)),
            addO(date.get(Calendar.MONTH)),
            addO(date.get(Calendar.YEAR)),
            addO(date.get(Calendar.HOUR_OF_DAY)),
            addO(date.get(Calendar.MINUTE)),
            addO(date.get(Calendar.SECOND))
        )

        println(formattedDate)
    }

}