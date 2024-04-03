package enumtest

enum class Result {
    SUCCESS {
        override fun log(message: String) {
            println("Success: $message")
        }
    },
    FAILURE {
        override fun log(message: String) {
            println("Failure: $message")
        }
    };

    abstract fun log(message: String)
}
