package getset


enum class Level {
    INFO, DEBUG, WARN, ERROR
}

class GetT {
    val value: String get() {
        println("Tu get là ?")
        return "Oui"
    }
}
