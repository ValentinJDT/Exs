package ignorefunc

fun <T> T.exec(callback: (T.() -> T)? = null): T = callback?.let { it(this) } ?: this
