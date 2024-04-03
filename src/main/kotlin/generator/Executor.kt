package generator

class Executor {

    companion object {
        fun <T, V> run(generator: Class<out Generator<T, V>>, value: T): V {
            return generator.getDeclaredConstructor().newInstance().execute(value)
        }
    }

}