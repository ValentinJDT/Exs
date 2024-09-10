package flowchannels

data class Thing (val name: String) {
    val count: Int
        get() = name.length
}
