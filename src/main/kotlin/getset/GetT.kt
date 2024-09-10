package getset

class GetT {
    val value: String get() {
        println("Tu get là ?")
        return "Oui"
    }
}
