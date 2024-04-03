package test.target

import heritage.Her
import heritage.test
import test.Test
import test.TestClass

class HerTest: TestClass() {


    @Test
    fun `test it`() {

        val instance  = Her()

        test<Her>()
    }

}
