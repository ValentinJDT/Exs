package test.target

import inlinerecursive.CustomClassDTO
import inlinerecursive.DTOMaker
import test.Test
import test.TestClass

class InlineCircleCallTest: TestClass() {

    @Test
    fun `remove s in firsts chars`() {
        val resp = DTOMaker.fake(CustomClassDTO::class)
        println(resp)
    }

}

inline fun <reified T> getType(listClass: List<T>): String? {
    return T::class.qualifiedName
}
