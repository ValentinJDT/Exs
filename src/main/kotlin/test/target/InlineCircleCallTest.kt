package test.target

import inlinerecursive.testRecursive
import test.Test
import test.TestClass
import test.assertEq

class InlineCircleCallTest: TestClass() {

    @Test
    fun `remove s in firsts chars`() {
        val value = "ssssa123"
        val result = testRecursive(value)

        assertEq("a123", result)
    }

}
