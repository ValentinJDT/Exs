package test.target

import generator.Executor
import generator.MyCustomNameGenerator
import generator.MyNumberGenerator
import generator.TextGenerator
import test.Test
import test.TestClass

class ExecutorTest: TestClass() {

    @Test
    fun `generate string`() {
        val value = Executor.run(MyCustomNameGenerator::class.java, 5)

        println(value)
    }

    @Test
    fun `generate int from string`() {
        val value = Executor.run(MyNumberGenerator::class.java, "ouigiopqzdzdq")
        println(value)
    }

    @Test
    fun `remove separator from string`() {
        val value = Executor.run(TextGenerator::class.java, "hello-i-ammmm-meeee")
        println(value)
    }
}