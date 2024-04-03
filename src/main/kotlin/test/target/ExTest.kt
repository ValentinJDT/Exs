package test.target

import test.Test
import test.TestClass
import test.assertEq

class ExTest : TestClass() {

    @Test
    fun `callback`() {

        fun tts(text: () -> String) = run { text.invoke() }

        val output = tts {
            var test = "bonsoir";
            test += " d";
            test
        }

        assertEq(output, "bonsoir d")
    }
}