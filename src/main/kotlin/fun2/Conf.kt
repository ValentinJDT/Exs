package fun2

class Conf(private var map: HashMap<String, Any> = HashMap()) {

    fun add(key: String, value: Any) {
        this.map[key] = value
    }

    fun edit(block: Conf.() -> Conf): Conf {
        return block(this)
    }

    fun get(key: String) = this.map[key]

    override fun toString(): String = this.map.toString()
}
