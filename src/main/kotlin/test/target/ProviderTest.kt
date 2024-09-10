package test.target

import provider.FakePlugin
import provider.Plugin
import provider.Provider
import test.Test
import test.TestClass
import test.*
import java.util.*

class ProviderTest: TestClass() {

    @Test
    fun `find correct class`() {

        val list = ArrayList<Plugin>()
        list.add(FakePlugin())

        val provider = Provider(list)

        assertNotNull(provider.get<FakePlugin>())
    }

    @Test(true)
    fun `can't find class`() {
        val list = ArrayList<String>()

        val provider = Provider(list)

        provider.get<String>()
    }

    @Test
    fun `can't fin class but return an other instance`() {
        val list = ArrayList<String>()

        val provider = Provider(list)

        assertEq(provider.getOrElse<String>("Yay"), "Yay")
    }

    @Test
    fun `can't fin class and return null`() {
        val list = ArrayList<String>()

        val provider = Provider(list)

        assertNull(provider.getOrNull<String>())
    }
}
