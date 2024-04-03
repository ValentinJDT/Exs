package deferedvalue

import kotlinx.coroutines.*

class DeferedClassVal {

    val value: Deferred<String> = defer {
        println("Defered value")
        "42"
    }

    override fun toString(): String {

        val va = runBlocking { value.await() }
        return va
    }

}

/** Executing coroutine and return value [T]. */
fun <T> defer(block: suspend CoroutineScope.() -> T) =
    CoroutineScope(Dispatchers.IO).async(start = CoroutineStart.LAZY, block = block)
