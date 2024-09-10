package test.target

import test.Test
import test.TestClass
import test.assertEq

class ForeachTest: TestClass() {

    @Test
    fun `return on forEach`() {

        val list = listOf("a", "b", "c")
        var count = 0
        list.forEach {
            if (it == "b") {
                return@forEach
            }
            count++
        }

        assertEq(2, count)
    }

}
