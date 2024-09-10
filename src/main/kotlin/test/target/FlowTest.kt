package test.target

import flowchannels.Thing
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import test.Qualifier
import test.Test
import test.TestClass
import test.assertEq

class FlowTest : TestClass() {

    override fun beans(): Map<String, Any> = mapOf(
        "thingFlow" to flow {
            emit(Thing("Thing"))
        }
    )

    @Test
    fun `get correct value`(@Qualifier("thingFlow") flow: Flow<Thing>) = runBlocking {
        val value = flow.first()

        assertEq(value.name, "Thing")
    }


}
