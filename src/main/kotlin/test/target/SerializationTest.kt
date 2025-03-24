package test.target

import com.iknova.gl.structdata4.core.typed
import outputsave.OutputSave
import outputsave.Person
import serialization.DataSerializationStruct
import serialization.EnumToSerialize
import serialization.MasterSerialize
import serialization.RandomToSerialize
import test.Test
import test.TestClass
import test.assertEq

class SerializationTest : TestClass() {

//    @Test
//    fun `deserialize with enum`() {
//        val dataSerializer = DataSerializationStruct()
//        val a = RandomToSerialize(1, "test", EnumToSerialize.A, "Oui".typed)
//        val b = RandomToSerialize(2, "test2", EnumToSerialize.B, "Non".typed)
//        val c = RandomToSerialize(3, "test3", EnumToSerialize.C, "Peut-être".typed)
//        val d = RandomToSerialize(4, "test4", EnumToSerialize.D, "Jamais".typed)
//
//        val first = MasterSerialize(a.typed, b.typed, c.typed, d.typed)
//
//        val data = dataSerializer.encodeToByteArray(first)
//
//        println(String(data))
//
//        assertEq(String(data), """{"a":1,"b":"test","c":"A","data":{"W":"Oui"}}""")
//    }

    @Test
    fun `output save`() {
        OutputSave.export(Person(
            "Victor",
            12,
            emptyMap()
        ))

        val another = OutputSave.import<Person>()
        println(another)
    }

}