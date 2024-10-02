package editlong

import java.time.ZoneId
import java.time.ZonedDateTime

class MyLongEdit {

    val dates = listOf(
        dateTime(),
        dateTime(),
        dateTime(),
        dateTime(),
        dateTime(),
        dateTime(),
        dateTime(),
    )

    var currLong = 0L

    fun run() {
        func()
    }

    fun func() {
        println("start latest: $currLong")

        dates.forEach {
            if (currLong > it.toInstant().toEpochMilli()) return@forEach
            currLong = it.toInstant().toEpochMilli()
        }

        println("end latest: $currLong")
    }

    fun dateTime() = ZonedDateTime.of(
        (2021..2024).random(),
        (1..12).random(),
        (1..28).random(),
        (0..23).random(),
        (0..59).random(),
        (0..59).random(),
        0,
        ZoneId.of("Europe/Paris")
    )


}
