package console

class ConsoleTrick {

    companion object {
        fun addLine(message: String) {
            println(message)
        }

        fun replaceLine(message: String) {
            println("\r$message")
        }

        fun changeLastLine(message: String) {
            println("\u001B[A$message")
        }

        fun deleteLastLine() {
            println("\u001B[M")
        }

        fun clearLines() {
            println("\u001B[J")
        }
    }

}