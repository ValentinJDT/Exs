package test.target

import dbreader.DbReader
import test.Test
import test.TestClass

class DbReaderTest: TestClass() {

    override fun beans(): Map<String, Any> = mapOf(
        DbReader::class.java.name to DbReader(),
    )

    @Test
    fun dbRead(reader: DbReader) {

        reader.readData()

    }
}