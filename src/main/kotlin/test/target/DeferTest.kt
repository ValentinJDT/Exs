package test.target

import deferedvalue.DeferedClassVal
import kotlinx.coroutines.runBlocking
import test.Test
import test.TestClass

class DeferTest: TestClass() {

    @Test
    fun defer() {
        val instance = DeferedClassVal()

        runBlocking {
            println(instance.value.await())
        }

    }

}
