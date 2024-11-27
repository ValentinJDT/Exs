package test.target

import com.iknova.gl.structdata4.core.typed
import serialization.DataSerializationStruct
import serialization.EnumToSerialize
import serialization.MasterSerialize
import serialization.ToSerialize
import test.Test
import test.TestClass

class SerializationTest : TestClass() {

    @Test
    fun `deserialize with enum`() {
        val dataSerializer = DataSerializationStruct()
        val a = ToSerialize(1, "test", EnumToSerialize.A, "Oui".typed)
        val b = ToSerialize(2, "test2", EnumToSerialize.B, "Non".typed)
        val c = ToSerialize(3, "test3", EnumToSerialize.C, "Peut-être".typed)
        val d = ToSerialize(4, "test4", EnumToSerialize.D, "Jamais".typed)


        val first = MasterSerialize(a.typed, b.typed, c.typed, d.typed)

        val data = dataSerializer.encodeToByteArray(first)

        println(String(data))
        println(first)

    // assertEq(String(data), """{"a":1,"b":"test","c":"A","data":{"W":"Oui"}}""")

    }

}