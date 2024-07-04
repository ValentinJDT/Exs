package callbacksame

interface MappingOuput<T> {
    val items: List<T>
}

fun <T : Any, K : Any> List<T>.mapping(mapper: ((T) -> K)? = null): List<Any> = mapper?.run {
    map { this.invoke(it) }
} ?: this
