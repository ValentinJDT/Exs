package coroutines


suspend fun <T> T.test(block: suspend T.() -> T): T {
    return block()
}
