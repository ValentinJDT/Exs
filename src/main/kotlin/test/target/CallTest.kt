package test.target

import abstractcalls.Call1
import abstractcalls.Call2
import test.Test
import test.TestClass

class CallTest: TestClass() {

    @Test
    fun `execute by super function`() {

        Call1()
    }

}
