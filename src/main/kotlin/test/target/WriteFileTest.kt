package test.target

import fileprinter.writeFile
import test.*
import java.io.File

class WriteFileTest: TestClass() {

    override fun beans(): Map<String, Any> = mapOf(
        "data" to StaticData()
    )

    @Test(order = 1)
    fun `write file`(@Qualifier("data") data: StaticData) {
        val file = writeFile("text.txt") {
            print("Hello")
        }

        assertTrue(file.exists())
        assertEq(file.readText(), "Hello")

        data["currentFile"] = file
    }

    @Test(order = 2)
    fun `read created file`(@Qualifier("data") data: StaticData) {
        val file: File = assertNotNull(data["currentFile"])

        assertTrue(file.exists())
        assertEq(file.readText(), "Hello")
    }
}
