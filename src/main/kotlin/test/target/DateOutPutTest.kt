package test.target

import test.Test
import test.TestClass
import test.toBean
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class DateOutPutTest: TestClass() {

    override fun beans(): Map<String, Any> {
        return mapOf(ZonedDateTime.now().toBean())
    }


    @Test
    fun `date output`(date: ZonedDateTime) {
        println("Date: ${formatDate(date)}")
    }

    fun formatDate(date: ZonedDateTime): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss")
        return date.format(formatter)
    }


}