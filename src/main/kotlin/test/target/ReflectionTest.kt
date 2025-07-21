package test.target

import reflection.MySuperClass
import reflection.execute
import reflection.executeBis
import reflection.getTestValue
import test.Test
import test.TestClass
import test.assertEq

class ReflectionTest: TestClass() {

    @Test
    fun `get data with reflection`(){
        val instance = MySuperClass("MyName", 42)
        assertEq(instance.getTestValue(), "reading")
    }

    @Test
    fun `get data with execute function`() {
        val instance = MySuperClass("MyName", 65)
        val output = execute<String>(instance, "getData().getTest()")
        assertEq(output, "reading")
    }

    @Test
    fun `get data with executeBis function`() {
        val instance = MySuperClass("MyName", 65)
        val firstOutput = executeBis<String>(instance, "superClassData.getTest()")
        val secondOutput = executeBis<String>(instance, "getData().test")
        val thirdOutput = executeBis<String>(instance, "superClassData.test")

        assertEq(firstOutput, "reading")
        assertEq(secondOutput, "reading")
        assertEq(thirdOutput, "reading")
    }

}