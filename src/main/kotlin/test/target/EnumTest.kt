package test.target

import enumtest.Result
import test.Test
import test.TestClass
import kotlin.random.Random

class EnumTest: TestClass() {

    @Test
    fun `run in results`() {
        val result = if(Random.nextInt(10) >= 5) Result.SUCCESS else Result.FAILURE

        result.log("This is nice, or maybe not : " + result.name)
    }
}
