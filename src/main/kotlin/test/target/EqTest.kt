package test.target

import test.Test
import test.TestClass
import test.assertEq


class EqTest: TestClass() {

    @Test(true)
    fun `verify eq asset`() {
        assertEq(false, true);
    }
}