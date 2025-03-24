package getset

class SetT {
    private val list = mutableListOf<Int>()

    operator fun set(key: Int, value: Int) {
        println("Set value: ${key + value}")
        list.add(key + value)
    }

    operator fun get(key: Int): Int {
        println("Get value: ${list[key]}")
        return list[key]
    }
}