package test.target

import ignorefunc.exec
import test.Test
import test.TestClass

class IgnoredRun: TestClass() {

    @Test
    fun `try to ignore middle run`() {

        val value = "My value :"

        println(value.exec().substring(2))
    }

    @Test
    fun `run middle function`() {

        val value = "My value :"

        println(value.exec{ "$this Beautiful" }.substring(2))
    }
}
