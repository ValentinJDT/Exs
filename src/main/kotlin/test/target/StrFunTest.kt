package test.target

import fun2.Conf
import strfun.invoke
import test.Test
import test.TestClass
import test.assertEq

class StrFunTest : TestClass() {

    @Test(true)
    fun `freeze program`() {
        "Correct Value"("test")
    }

    @Test
    fun run() {
        val t = Conf()
        t.edit {
            add("key", "ma value")
            this
        }

        assertEq(t.get("key"), "ma value")
    }
}
