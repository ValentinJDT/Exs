package test.target

import getset.GetT
import test.Test
import test.TestClass

class GetSetTest: TestClass() {

    @Test
    fun `println when get`() {

        val instance = GetT()

        instance.value
        println(instance.value)
    }
}
