package setget

interface CanMutate<Mutator> {
    suspend fun mutate(update: Boolean = true, block: suspend Mutator.() -> Unit): Unit
}
