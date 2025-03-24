package dbreader

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.time.Duration
import java.time.ZoneOffset
import kotlin.time.measureTime
import kotlin.use

class DbReader {

    private fun ResultSet.getZonedTimestamp(col: String = "IDDateTime") =
        getTimestamp(col).toLocalDateTime().atZone(ZoneOffset.UTC)

    fun Connection.readTArticoliSto() {
        val result = prepareStatement("SELECT * FROM TArticoliSto").executeQuery()

        while (result.next()) {
            val dateTime = result.getZonedTimestamp()
            val loadingTime = result.getZonedTimestamp("IDCarico")
            val toolNb = result.getString("IDLotto")?.takeIf { it.isNotBlank() }
            val loadingPos = result.getBigDecimal("IDPosizioneCarico")
            val program = result.getString("IDArticolo")
            val quantity = result.getDouble("Quantita")
            val superficie1 = result.getBigDecimal("Superficie1")
            val superficie2 = result.getBigDecimal("Superficie2")
        }
    }


    fun Connection.readTVascaSto() {
        val result = prepareStatement("SELECT * FROM TVascaSto").executeQuery()
        while (result.next()) {
            val dateTime = result.getZonedTimestamp()
            val loadingTime = result.getZonedTimestamp("IDCarico")
            val position = result.getBigDecimal("IDPosizione")
            val program = result.getString("IDArticolo")?.takeIf { it.isNotBlank() } ?: "N/A"
            val startTime = result.getZonedTimestamp("Entrata")
            val endTime = result.getZonedTimestamp("Uscita")
            val duration = Duration.between(startTime, endTime)
        }
    }

    fun readData() = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\v.jeandot\\Desktop\\Galv2000_STO.MDB")
        .use {

            val a = measureTime {
                it.readTArticoliSto()
            }

            println("Fetch time (a) : $a")

            val b = measureTime {
                it.readTVascaSto()
            }

            println("Fetch time (b) : $b")
        }

}