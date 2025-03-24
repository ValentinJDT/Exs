package test.target

import compare.json.ComparableClass
import compare.json.Comparation
import compare.json.CompareResult
import compare.json.serializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import test.Test
import test.TestClass

class CompareTest : TestClass() {

    @Test
    fun `compare two instances`() {
        val a = ComparableClass("first", 12)
        a["id"] = "qzdiohqzduipbq"
        a["cause"] = "cause"
        a["action"] = "action"
        a["timestamp"] = "1234567890L"

        a.setMetadata(
            "width" to 12f,
            "height" to 186f,
        )

        val sa = serializer.encodeToString(a)
        val b =
            serializer.decodeFromJsonElement<ComparableClass>(serializer.parseToJsonElement(sa)).copy(name = "second")
        // b["id"] = "coucou"
        b.age = null

        val result: CompareResult = Comparation<ComparableClass>().compare(a, b)

        println(Json.encodeToString(result))
    }

}