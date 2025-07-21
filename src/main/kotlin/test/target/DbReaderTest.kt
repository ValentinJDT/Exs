package test.target

import dbreader.DbReader
import test.Test
import test.TestClass
import test.toBean

class DbReaderTest: TestClass() {

    override fun beans(): Map<String, Any> = mapOf(
        DbReader().toBean(),
    )

    @Test
    fun dbRead(reader: DbReader) {

        reader.readData()

    }
}