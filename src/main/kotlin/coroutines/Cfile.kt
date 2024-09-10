package coroutines


suspend fun <T> T.test(block: suspend T.() -> T): T = block()
