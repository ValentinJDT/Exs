package test.target

import test.Test
import test.TestClass
import test.assertNotEq
import java.io.File

class SymlinkTest: TestClass() {

    @Test
    fun `list files from windows symlink`() {
        val folder = File("C:\\Users\\v.jeandot\\Desktop\\test\\Logs\\workfao")

        for (file in folder.listFiles()) {
            println(file.name)
        }

        assertNotEq(folder.listFiles().size, 0)
    }

}