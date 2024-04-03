package generator

interface Generator<in T, out V> {
    fun execute(value: T): V
}