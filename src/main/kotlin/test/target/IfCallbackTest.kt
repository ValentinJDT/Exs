package test.target

import org.slf4j.LoggerFactory
import test.Test
import test.TestClass
import test.assertEq
import test.assertNotNull

class IfCallbackTest: TestClass() {

    @Test
    fun `run callback if value is null to get default value`() {
        val result = null ?: run {
            "default value"
        }

        assertNotNull(result)
        assertEq(result, "default value")
    }

}