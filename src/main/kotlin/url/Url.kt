package url

enum class Path(val path: String) {
    GLOBAL("/api/default"),
    PERSONAL("/api/{uuid}/personal"),
    INFORMATION("/api/{uuid}/information"),
    POSTS("/api/product/{productId}/posts"),
    PRODUCTS("/api/product/{productId}"),
    NOT_FOUND("/api/404");

    companion object {
        fun getRoute(query: String): Path {
            return Path.values().find { eq(it.path, query) } ?: NOT_FOUND
        }
    }

    fun extractArgs(query: String): Map<String, String> {
        return isolate(this.path, query);
    }

}

fun eq(path: String, query: String): Boolean {
    val pathElements = path.split("/")
    val queryElements = query.split("/")

    return pathElements.size == queryElements.size &&
            pathElements.zip(queryElements).all { (pathElement, queryElement) ->
                pathElement == queryElement || pathElement.isPathParam()
            }
}

private fun String.isPathParam() = startsWith("{") && endsWith("}")

fun isolate(path: String, query: String): Map<String, String> {

    val args = mutableMapOf<String, String>()

    val pathElements = path.split("/")
    val queryElements = query.split("/")

    if(pathElements.size != queryElements.size)
        return args

    for(i in queryElements.indices) {
        val pathElement = pathElements[i]
        val queryElement = queryElements[i]

        if(pathElement.isPathParam()) {
            args[pathElement.drop(1).dropLast(1)] = queryElement
        }
    }

    return args
}