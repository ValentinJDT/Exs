package test.target

import autoclose.TempFile
import autoclose.tempFile
import test.Test
import test.TestClass
import test.assertEq
import test.assertFalse

class AutoCloseTest: TestClass() {

    @Test
    fun `create, read and delete temp file`() {
        val tempFile = TempFile()

        // Auto remove the file after the block
        tempFile.use {
            it.writeLine("Hello, World!")
            assertEq(it.content, "Hello, World!")
        }

        assertFalse(tempFile.exists)
    }

    @Test
    fun `create, read and delete temp file with function`() {
        val file = tempFile {
            writeLine("Hello, World!")
            assertEq(content, "Hello, World!")
        }

        assertFalse(file.exists)
    }

}