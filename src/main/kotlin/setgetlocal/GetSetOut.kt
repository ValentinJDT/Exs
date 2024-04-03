package setgetlocal

import kotlinx.coroutines.delay
import java.util.*

interface DAO {
    suspend fun update(newVal: String)

    val value: String

    suspend fun getTimestamp() : Long
}

class PseudoModel(private val dao: DAO) {

    val value: String get() = dao.value

    suspend fun mutate(block: suspend TotoMutator.() -> Unit) {
        val mutator = object : TotoMutator {
            override var value = this@PseudoModel.value
        }
        mutator.block()
        dao.update(mutator.value)
    }

    interface TotoMutator {
        var value: String
    }
}

val daoImpl = object : DAO {
    override suspend fun update(newVal: String) {
        delay(1000L)
        //println("Updating into DB with newVal = $newVal")
        value = newVal
    }

    override var value: String = "Initial value"

    override suspend fun getTimestamp(): Long = Date().time

}
