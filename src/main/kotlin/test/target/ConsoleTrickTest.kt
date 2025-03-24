package test.target

import console.ConsoleTrick
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import test.Test
import test.TestClass

class ConsoleTrickTest: TestClass() {

    @Test
    fun `replace current line`() = runBlocking {
        ConsoleTrick.addLine("Yo")
    }

}