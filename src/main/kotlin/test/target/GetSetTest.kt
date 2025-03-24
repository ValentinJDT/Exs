package test.target

import getset.GetT
import getset.SetT
import test.Test
import test.TestClass

class GetSetTest: TestClass() {

    @Test
    fun `println when get`() {

        val instance = GetT()

        instance.value
        println(instance.value)
    }

    @Test
    fun `addition when set`() {

        val instance = SetT()

        instance[12] = 5

        println(instance[0])
    }
}
