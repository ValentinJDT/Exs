package test.target

import builder.Human
import test.*

class StaticDataTest : TestClass() {

    override fun beans() = mapOf(
        "customData" to StaticData(),
        "typedStaticData" to TypedStaticData<String>()
    )

    @Test(order = 1)
    fun `static data`(data: StaticData) {
        data["my-key"] = 10
    }

    @Test(order = 2)
    fun `get static data`(data: StaticData) {
        val value: Int = assertNotNull(data["my-key"])

        assertEq(value, 10)
    }

    @Test(order = 3)
    fun `static push custom class`(data: StaticData) {
        val human = Human.Builder("John").arms(2).legs(2).build()
        data["custom"] = human
    }

    @Test(order = 4)
    fun `static get custom class`(data: StaticData) {
        val human: Human = assertNotNull(data["custom"])
        val nullablehuman: Human? = data["custom-uwu"]

        assertNull(nullablehuman)
        assertEq(human.name, "John")
    }

    @Test(order = 5)
    fun `dual static data`(data: StaticData, @Qualifier("customData") otherData: StaticData) {
        println(data)
        println(otherData)

        assertNotEq(data.toString(), otherData.toString())
    }

    @Test(order = 6)
    fun `typed static data`(@Qualifier("typedStaticData") data: TypedStaticData<String>) {
        data["set1"] = "Hello"
    }

    @Test(order = 7)
    fun `get typed static data`(@Qualifier("typedStaticData") data: TypedStaticData<String>) {
        val value = assertNotNull(data["set1"])

        assertEq(value, "Hello")
    }

}
