package test.target

import callbacksame.mapping
import test.Test
import test.TestClass

class CBackMappingTest: TestClass() {

    @Test
    fun `default mapping`() {
        listOf(1, 2, 3).mapping<Int, Any>().forEach {
            println(it)
        }
    }

}
