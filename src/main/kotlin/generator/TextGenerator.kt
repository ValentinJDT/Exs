package generator


class TextGenerator: Generator<String, String> {

    override fun execute(value: String): String {
        return value.undashify()
    }

    private fun String.undashify() = split('-').mapIndexed { i, s -> if (i == 0) s else s[0].uppercase() + s.substring(1) }.joinToString("")

}